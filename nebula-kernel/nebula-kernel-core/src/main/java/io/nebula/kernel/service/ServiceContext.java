package io.nebula.kernel.service;

import io.nebula.kernel.classloader.ThreadLocalManager;
import io.nebula.kernel.entity.BaseEntity;
import io.nebula.kernel.security.IUser;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/24
 */
@Data
public class ServiceContext {
    private static final ThreadLocal<ServiceContext> REQUEST_CONTEXT = ThreadLocalManager.createThreadLocal(ServiceContext.class);
    /**
     * 当前用户
     */
    protected IUser currentUser;

    /**
     * Http请求
     */
    protected HttpServletRequest request;

    /**
     * Http响应
     */
    protected HttpServletResponse response;

    /**
     * 单例模型
     *
     * @return
     */
    public synchronized static ServiceContext instance() {
        ServiceContext instance = REQUEST_CONTEXT.get();
        if (null == instance) {
            instance = new ServiceContext();
            REQUEST_CONTEXT.set(instance);
        }
        return instance;
    }

    /**
     * 获取当前用户
     *
     * @param <T>
     * @return
     */
    public static <T extends IUser> T currentUser() {
        return (T) instance().currentUser;
    }

    /**
     * 内容清除
     */
    public void clear() {
        REQUEST_CONTEXT.remove();
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public String getUserName() {
        if (null != currentUser) {
            return currentUser.getUserName();
        }
        return null;
    }

    /**
     * 填充创建人、创建时间
     *
     * @param entity
     */
    public void preCreate(BaseEntity entity) {
        if (null != entity && null != currentUser) {
            entity.setCreatedBy(getUserName());
            entity.setCreated(new Date());
        }
    }

    /**
     * 填充修改人、修改时间
     *
     * @param entity
     */
    public void preUpdate(BaseEntity entity) {
        if (null != entity && null != currentUser) {
            entity.setUpdatedBy(getUserName());
            entity.setUpdated(new Date());
        }
    }
}
