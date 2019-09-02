package io.nebula.util;

import java.lang.reflect.*;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/11
 */
public class ClassUtil {
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];

    @SuppressWarnings("unchecked")
    public static <T> Class<T>[] emptyClasses() {
        return EMPTY_CLASS_ARRAY;
    }

    /**
     * Returns single component type. Index is used when type consist of many
     * components. If negative, index will be calculated from the end of the
     * returned array. Returns <code>null</code> if component type
     * does not exist or if index is out of bounds.
     *
     * @see #getComponentTypes(java.lang.reflect.Type)
     */
    public static Class getComponentType(final Type type, final int index) {
        return getComponentType(type, null, index);
    }

    /**
     * Returns single component type for given type and implementation.
     * Index is used when type consist of many
     * components. If negative, index will be calculated from the end of the
     * returned array.  Returns <code>null</code> if component type
     * does not exist or if index is out of bounds.
     * <p>
     *
     * @see #getComponentTypes(java.lang.reflect.Type, Class)
     */
    public static Class getComponentType(final Type type, final Class implClass, int index) {
        Class[] componentTypes = getComponentTypes(type, implClass);
        if (componentTypes == null) {
            return null;
        }

        if (index < 0) {
            index += componentTypes.length;
        }

        if (index >= componentTypes.length) {
            return null;
        }

        return componentTypes[index];
    }

    /**
     * @see #getComponentTypes(java.lang.reflect.Type, Class)
     */
    public static Class[] getComponentTypes(final Type type) {
        return getComponentTypes(type, null);
    }

    /**
     * Returns all component types of the given type.
     * For example the following types all have the
     * component-type MyClass:
     * <ul>
     * <li>MyClass[]</li>
     * <li>List&lt;MyClass&gt;</li>
     * <li>Foo&lt;? extends MyClass&gt;</li>
     * <li>Bar&lt;? super MyClass&gt;</li>
     * <li>&lt;T extends MyClass&gt; T[]</li>
     * </ul>
     */
    public static Class[] getComponentTypes(final Type type, final Class implClass) {
        if (type instanceof Class) {
            Class clazz = (Class) type;
            if (clazz.isArray()) {
                return new Class[]{clazz.getComponentType()};
            }
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;

            Type[] generics = pt.getActualTypeArguments();

            if (generics.length == 0) {
                return null;
            }

            Class[] types = new Class[generics.length];

            for (int i = 0; i < generics.length; i++) {
                types[i] = getRawType(generics[i], implClass);
            }
            return types;
        } else if (type instanceof GenericArrayType) {
            GenericArrayType gat = (GenericArrayType) type;

            Class rawType = getRawType(gat.getGenericComponentType(), implClass);
            if (rawType == null) {
                return null;
            }

            return new Class[]{rawType};
        }
        return null;
    }

    /**
     * Shortcut for <code>getComponentTypes(type.getGenericSuperclass())</code>.
     *
     * @see #getComponentTypes(java.lang.reflect.Type)
     */
    public static Class[] getGenericSupertypes(final Class type) {
        return getComponentTypes(type.getGenericSuperclass());
    }

    /**
     * Shortcut for <code>getComponentType(type.getGenericSuperclass())</code>.
     *
     * @see #getComponentType(java.lang.reflect.Type, int)
     */
    public static Class getGenericSupertype(final Class type, final int index) {
        return getComponentType(type.getGenericSuperclass(), index);
    }

    /**
     * Returns raw class for given <code>type</code>. Use this method with both
     * regular and generic types.
     *
     * @param type the type to convert
     * @return the closest class representing the given <code>type</code>
     * @see #getRawType(java.lang.reflect.Type, Class)
     */
    public static Class getRawType(final Type type) {
        return getRawType(type, null);
    }

    /**
     * Returns raw class for given <code>type</code> when implementation class is known
     * and it makes difference.
     *
     * @see #resolveVariable(java.lang.reflect.TypeVariable, Class)
     */
    public static Class<?> getRawType(final Type type, final Class implClass) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            return getRawType(pType.getRawType(), implClass);
        }
        if (type instanceof WildcardType) {
            WildcardType wType = (WildcardType) type;

            Type[] lowerTypes = wType.getLowerBounds();
            if (lowerTypes.length > 0) {
                return getRawType(lowerTypes[0], implClass);
            }

            Type[] upperTypes = wType.getUpperBounds();
            if (upperTypes.length != 0) {
                return getRawType(upperTypes[0], implClass);
            }

            return Object.class;
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            Class<?> rawType = getRawType(genericComponentType, implClass);
            // this is sort of stupid, but there seems no other way (consider don't creating new instances each time)...
            return Array.newInstance(rawType, 0).getClass();
        }
        if (type instanceof TypeVariable) {
            TypeVariable<?> varType = (TypeVariable<?>) type;
            if (implClass != null) {
                Type resolvedType = resolveVariable(varType, implClass);
                if (resolvedType != null) {
                    return getRawType(resolvedType, null);
                }
            }
            Type[] boundsTypes = varType.getBounds();
            if (boundsTypes.length == 0) {
                return Object.class;
            }
            return getRawType(boundsTypes[0], implClass);
        }
        return null;
    }

    /**
     * Resolves <code>TypeVariable</code> with given implementation class.
     */
    public static Type resolveVariable(final TypeVariable variable, final Class implClass) {
        final Class rawType = getRawType(implClass, null);

        int index = ArrayUtil.indexOf(rawType.getTypeParameters(), variable);
        if (index >= 0) {
            return variable;
        }

        final Class[] interfaces = rawType.getInterfaces();
        final Type[] genericInterfaces = rawType.getGenericInterfaces();

        for (int i = 0; i <= interfaces.length; i++) {
            Class rawInterface;

            if (i < interfaces.length) {
                rawInterface = interfaces[i];
            } else {
                rawInterface = rawType.getSuperclass();
                if (rawInterface == null) {
                    continue;
                }
            }

            final Type resolved = resolveVariable(variable, rawInterface);
            if (resolved instanceof Class || resolved instanceof ParameterizedType) {
                return resolved;
            }

            if (resolved instanceof TypeVariable) {
                final TypeVariable typeVariable = (TypeVariable) resolved;
                index = ArrayUtil.indexOf(rawInterface.getTypeParameters(), typeVariable);

                if (index < 0) {
                    throw new IllegalArgumentException("Invalid type variable:" + typeVariable);
                }

                final Type type = i < genericInterfaces.length ? genericInterfaces[i] : rawType.getGenericSuperclass();

                if (type instanceof Class) {
                    return Object.class;
                }

                if (type instanceof ParameterizedType) {
                    return ((ParameterizedType) type).getActualTypeArguments()[index];
                }

                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        }
        return null;
    }
}
