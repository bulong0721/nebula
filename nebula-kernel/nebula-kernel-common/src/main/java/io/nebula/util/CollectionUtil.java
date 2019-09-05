package io.nebula.util;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The Class Collections.
 * <pre>尽量不用commons下的CollectionUtils,因为CollectionUtils会在web容器中引用，
 * 因java1.5 ClassLoader的原因，所引用的jar包版本与web容器中引用的版本不同，
 * 可能加载失败，导致项目不能启动</pre>
 */
public class CollectionUtil {

    private static int INDEX_NOT_FOUND = -1;

    /**
     * Checks if is empty.
     *
     * @param collection the collection
     * @return true, if is empty
     */
    public static boolean isEmpty(final Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * Checks if is not empty.
     *
     * @param collection the collection
     * @return true, if is not empty
     */
    public static boolean isNotEmpty(final Collection<?> collection) {
        return !(isEmpty(collection));
    }

    /**
     * Gets the one.
     *
     * @param <T>  the generic type
     * @param list the list
     * @return the one
     */
    public static <T> T getOne(final List<T> list) {
        if (isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    public static <K, V> V getOne(Map<K, V> map) {
        if (isNotEmpty(map)) {
            Iterator<V> iterable = map.values().iterator();
            return iterable.next();
        }
        return null;
    }

    public static <K, V> K getOneKey(Map<K, V> map) {
        if (isNotEmpty(map)) {
            Iterator<K> iterable = map.keySet().iterator();
            return iterable.next();
        }
        return null;
    }

    public static <K, V> V get(Map<K, V> map, K key) {
        if (isNotEmpty(map)) {
            return map.get(key);
        }
        return null;
    }

    public static <K, V> List<K> getKeys(Map<K, V> map) {
        if (isNotEmpty(map)) {
            return new ArrayList<K>(map.keySet());
        }
        return new ArrayList<K>(0);
    }

    public static <K, V> List<V> getValues(Map<K, V> map) {
        if (isNotEmpty(map)) {
            return new ArrayList<V>(map.values());
        }
        return new ArrayList<V>(0);
    }

    /**
     * Return {@code true} if the supplied Map is {@code null} or empty.
     * Otherwise, return {@code false}.
     *
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * Checks if is not empty.
     *
     * @param map the map
     * @return true, if is not empty
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !(isEmpty(map));
    }

    /**
     * Checks if is empty.
     *
     * @param <T>   the generic type
     * @param array the array
     * @return true, if is empty
     */
    public static <T> boolean isEmpty(final T[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * Checks if is not empty.
     *
     * @param <T>   the generic type
     * @param array the array
     * @return true, if is not empty
     */
    @SafeVarargs
    public static <T> boolean isNotEmpty(final T... array) {
        return !(isEmpty(array));
    }

    /**
     * Index of.
     *
     * @param array        the array
     * @param objectToFind the object to find
     * @return the int
     */
    public static int indexOf(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind) >= 0;
    }

    /**
     * Index of.
     *
     * @param array        the array
     * @param objectToFind the object to find
     * @param startIndex   the start index
     * @return the int
     */
    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * Index of ignore case.
     *
     * @param array        the array
     * @param stringToFind the string to find
     * @return the int
     */
    public static int indexOfIgnoreCase(String[] array, String stringToFind) {
        if (isEmpty(array)) {
            return INDEX_NOT_FOUND;
        }

        if (stringToFind == null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            String strLower = stringToFind.toLowerCase();
            for (int i = 0; i < array.length; i++) {
                if (strLower.equals(StringUtil.toLowerCase(array[i]))) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getOneByType(Object[] array, Class<T> typeToFind) {
        if (isEmpty(array)) {
            return null;
        }
        for (Object object : array) {
            if (typeToFind.isInstance(object)) {
                return (T) object;
            }
        }
        return null;
    }

    public static <K, V> boolean hasValue(Map<K, V> map, Object key, Object value) {
        return (map != null) && (key != null) && (value != null) && value.equals(map.get(key));
    }

    public static List<String> toList(String ids, String split) {
        List<String> result = null;
        if (StringUtil.isNotBlank(ids)) {
            String[] idarray = ids.split(split);
            result = new ArrayList<String>();
            for (String id : idarray) {
                if (StringUtil.isNotBlank(id)) {
                    result.add(id);
                }
            }
        }
        return result;
    }

    public static List<Integer> toIntList(String ids, String split) {
        List<Integer> result = null;
        if (StringUtil.isNotBlank(ids)) {
            String[] idarray = ids.split(split);
            result = new ArrayList<Integer>();
            for (String id : idarray) {
                if (StringUtil.isNotBlank(id)) {
                    Integer intId = Integer.parseInt(id);
                    result.add(intId);
                }
            }
        }
        return result;
    }


    public static <V, T> Map<V, T> toMap(Collection<T> items, Function<? super T, V> getMethod) {
        if (CollectionUtil.isNotEmpty(items)) {
            return items.stream().filter(e -> {
                return e != null;
            }).collect(Collectors.toMap(getMethod, Function.identity(), (key1, key2) -> key2));
        }
        return new HashMap<V, T>(0);
    }

    public static <V, T> Map<V, List<T>> toGroup(Collection<T> items, Function<? super T, V> getMethod) {
        if (CollectionUtil.isNotEmpty(items)) {
            Map<V, List<T>> result = items.stream().filter(e -> {
                return e != null;
            }).collect(Collectors.groupingBy(getMethod));
            return result;
        }
        return new HashMap<V, List<T>>(0);
    }

    public static <V, T> List<V> toList(Collection<T> items, Function<? super T, V> getMethod) {
        if (CollectionUtil.isNotEmpty(items)) {
            List<V> result = getNotNullStream(items, getMethod).collect(Collectors.toList());
            return result;
        }
        return new ArrayList<V>(0);
    }

    public static <K, T> List<T> filterListToList(Map<K, T> itemMap, Collection<K> filterValues) {
        List<T> result = new ArrayList<T>();
        if (CollectionUtil.isNotEmpty(itemMap)) {
            for (K k : filterValues) {
                T t = itemMap.get(k);
                if (t != null) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    public static <K, T> Map<K, T> filterListToMap(Map<K, T> itemMap, Collection<K> filterValues) {
        Map<K, T> result = new LinkedHashMap<K, T>();
        if (CollectionUtil.isNotEmpty(itemMap)) {
            for (K k : filterValues) {
                T t = itemMap.get(k);
                if (t != null) {
                    result.put(k, t);
                }
            }
        }
        return result;
    }

    public static <V, T> Set<V> toSet(Collection<T> items, Function<? super T, V> getMethod) {
        if (CollectionUtil.isNotEmpty(items)) {
            Set<V> result = getNotNullStream(items, getMethod).collect(Collectors.toSet());
            return result;
        }
        return new HashSet<V>(0);
    }

    public static <V, D> Stream<V> getNotNullStream(Collection<D> items, Function<? super D, V> getMethod) {
        Stream<V> filteredStream = items.stream().filter(d -> {
            if (d != null) {
                return getMethod.apply(d) != null;
            }
            return false;
        }).map(getMethod);
        return filteredStream;
    }


    public static <T> List<T> arraysToList(T... arrays) {
        if (CollectionUtil.isEmpty(arrays)) {
            return new ArrayList<T>(0);
        }
        return Arrays.asList(arrays);
    }

    public static <D, K, S> void setListValue(Collection<D> dests, S value, BiConsumer<D, S> destSetMethod) {
        if (isNotEmpty(dests)) {
            for (D d : dests) {
                if (d != null) {
                    destSetMethod.accept(d, value);
                }
            }
        }
    }

    public static <K, D, S, V> void getAndSetValueByMap(Collection<D> dests, Function<? super D, K> destGetMethod, Map<K, S> sourceMap,
                                                        Function<? super S, Object> sourceGetMethod, BiConsumer<D, Object> destSetMethod) {
        if (CollectionUtil.isNotEmpty(dests) && (CollectionUtil.isNotEmpty(sourceMap))) {
            for (D d : dests) {
                if (d != null) {
                    K key = destGetMethod.apply(d);
                    S source = sourceMap.get(key);
                    if (source != null) {
                        Object sourceValue = sourceGetMethod.apply(source);
                        destSetMethod.accept(d, sourceValue);
                    }
                }
            }
        }
    }

    public static <D, K, S> void getAndSetModelByMap(Collection<D> dests, Map<K, S> sourceMap, Function<? super D, K> destGetMethod,
                                                     BiConsumer<D, S> destSetMethod) {
        if (CollectionUtil.isNotEmpty(dests) && CollectionUtil.isNotEmpty(sourceMap)) {
            for (D d : dests) {
                if (d != null) {
                    K key = destGetMethod.apply(d);
                    if (key != null) {
                        S s = sourceMap.get(key);
                        destSetMethod.accept(d, s);
                    }
                }
            }
        }
    }


    public static <D, K, S, V> void getAndSetFeildToFieldByMap(Collection<D> dests, Map<K, S> sourceMap, Function<? super D, K> destGetMethod,
                                                               Function<? super S, V> sourceGetMethod, BiConsumer<D, V> destSetMethod) {
        if (isNotEmpty(dests)) {
            for (D d : dests) {
                if (d != null) {
                    K key = destGetMethod.apply(d);
                    if (key != null) {
                        V v = null;
                        S s = sourceMap.get(key);
                        if (s != null) {
                            v = sourceGetMethod.apply(s);
                        }

                        if (v != null) {
                            destSetMethod.accept(d, v);
                        }
                    }
                }
            }
        }
    }

    public static <D, K, S> void getAndSetListByMap(List<D> dests, Map<K, List<S>> sourceListMap, Function<? super D, K> destGetMethod,
                                                    BiConsumer<D, List<S>> destSetMethod) {
        for (D d : dests) {
            if (d != null) {
                K key = destGetMethod.apply(d);
                if (key != null) {
                    List<S> list = sourceListMap.get(key);
                    destSetMethod.accept(d, list);
                }
            }
        }
    }


    public static <T> List<T> newEmptyList() {
        return new ArrayList<T>(0);
    }

    public static <T> Set<T> newEmptySet() {
        return new HashSet<T>(0);
    }

    public static <T> List<T> newArrayList(Collection<T> items) {
        if (items == null) {
            return newEmptyList();
        }
        return new ArrayList<T>(items);
    }

    public static <T> Set<T> newSet(Collection<T> items) {
        if (items == null) {
            return newEmptySet();
        }
        return new HashSet<T>(items);
    }

    public static <K, T> Map<K, T> newEmptyMap() {
        return new HashMap<K, T>(0);
    }

    public static <T, V> List<T> filter(List<T> sources, Function<? super T, V> sourceGetGetMethod, V filterValue) {
        return sources.stream().filter(d -> {
            if (d != null) {
                V v = sourceGetGetMethod.apply(d);
                if (filterValue == null) {
                    if (v == null) {
                        return true;
                    }
                    return false;
                }
                return filterValue.equals(v);
            }
            return false;
        }).collect(Collectors.toList());
    }


    public static <T, V> List<T> filterBySet(List<T> sources, Function<? super T, V> sourceGetMethod, Set<V> valueSet) {
        if (CollectionUtil.isEmpty(valueSet)) {
            return new ArrayList<T>(0);
        }

        if (CollectionUtil.isNotEmpty(sources)) {
            List<T> result = sources.stream().filter(d -> {
                if (d != null) {
                    V v = sourceGetMethod.apply(d);
                    return valueSet.contains(v);
                }
                return false;
            }).collect(Collectors.toList());
            return result;
        }
        return sources;
    }

    public static <T, ID, SUBID> Map<ID, Map<SUBID, T>> getIdSubIdObjectMap(List<T> sources, Function<? super T, ID> idGetMethod, Function<? super T, SUBID> subIdGetMethod) {
        if (CollectionUtil.isEmpty(sources)) {
            return CollectionUtil.newEmptyMap();
        }

        Map<ID, List<T>> idTListMap = CollectionUtil.toGroup(sources, idGetMethod);
        Map<ID, Map<SUBID, T>> idSubIdSetMap = new HashMap<ID, Map<SUBID, T>>(idTListMap.size());
        for (Entry<ID, List<T>> entry : idTListMap.entrySet()) {
            Map<SUBID, T> itemIdSet = CollectionUtil.toMap(entry.getValue(), subIdGetMethod);
            idSubIdSetMap.put(entry.getKey(), itemIdSet);
        }
        return idSubIdSetMap;
    }

    public static <T> boolean contains(Collection<T> items, T t) {
        return CollectionUtil.isNotEmpty(items) && items.contains(t);
    }

    public static <T, V> boolean contains(Map<T, V> map, T t) {
        return CollectionUtil.isNotEmpty(map) && map.containsKey(t);
    }


}
