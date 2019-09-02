package io.nebula.kernel.classloader;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 徐步龙
 */
public class ThreadLocalManager {

    private static final ThreadLocal<ThreadLocalManager> THREAD_LOCAL_MANAGER = new ThreadLocal<ThreadLocalManager>() {
        @Override
        protected ThreadLocalManager initialValue() {
            ThreadLocalManager manager = new ThreadLocalManager();
            String checkOrphans = System.getProperty("ThreadLocalManager.notify.orphans");
            if ("true".equals(checkOrphans)) {
                manager.marker = new RuntimeException("Thread Local Manager is not empty - the following is the culprit call that setup the thread local but did not clear it.");
            }
            return manager;
        }
    };
    private static final Object THREAD_LOCK = new Object();
    private static Long count = 0L;
    protected Map<Long, ThreadLocal> threadLocals = new LinkedHashMap<Long, ThreadLocal>();
    protected RuntimeException marker = null;

    public static void addThreadLocal(ThreadLocal threadLocal) {
        Long position;
        synchronized (THREAD_LOCK) {
            count++;
            position = count;
        }
        THREAD_LOCAL_MANAGER.get().threadLocals.put(position, threadLocal);
    }

    public static <T> ThreadLocal<T> createThreadLocal(final Class<T> type) {
        return createThreadLocal(type, true);
    }

    public static <T> ThreadLocal<T> createThreadLocal(final Class<T> type, final boolean createInitialValue) {
        ThreadLocal<T> response = new ThreadLocal<T>() {
            @Override
            protected T initialValue() {
                addThreadLocal(this);
                if (!createInitialValue) {
                    return null;
                }
                try {
                    return type.newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void set(T value) {
                super.get();
                super.set(value);
            }
        };
        return response;
    }

    public static void remove() {
        for (Map.Entry<Long, ThreadLocal> entry : THREAD_LOCAL_MANAGER.get().threadLocals.entrySet()) {
//            if (LOG.isDebugEnabled()) {
//                LOG.debug("Removing ThreadLocal #" + entry.getKey() + " from request thread.");
//            }
            entry.getValue().remove();
        }
        THREAD_LOCAL_MANAGER.get().threadLocals.clear();
        THREAD_LOCAL_MANAGER.remove();
    }

    public static void remove(ThreadLocal threadLocal) {
        Long removePosition = null;
        for (Map.Entry<Long, ThreadLocal> entry : THREAD_LOCAL_MANAGER.get().threadLocals.entrySet()) {
            if (entry.getValue().equals(threadLocal)) {
//                if (LOG.isDebugEnabled()) {
//                    LOG.debug("Removing ThreadLocal #" + entry.getKey() + " from request thread.");
//                }
                entry.getValue().remove();
                removePosition = entry.getKey();
            }
        }
        THREAD_LOCAL_MANAGER.get().threadLocals.remove(removePosition);
    }

    @Override
    public String toString() {
        if (!threadLocals.isEmpty() && marker != null) {
            marker.printStackTrace();
        }
        return super.toString();
    }
}
