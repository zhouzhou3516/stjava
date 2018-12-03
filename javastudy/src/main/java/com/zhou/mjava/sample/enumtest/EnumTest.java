package com.zhou.mjava.sample.enumtest;

import com.google.common.collect.Lists;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * @author liqingzhou on 18/10/18
 */
public class EnumTest {

    private static final List<Integer> LIST = Lists.newArrayList(1);

    public static void main(String[] args) {
//        test(ExtendedOperation.class, 2, 3);

        ;
//        LIST = Lists.newArrayList();
        A<Integer> a = new A<>();
        TypeVariable<?>[] typeParams = a.getClass().getTypeParameters();
        Type genericSuperType = a.getClass().getGenericSuperclass();

        Type[] actualTypeParams = ((ParameterizedType) genericSuperType).getActualTypeArguments();

        Type actualTypeParam = actualTypeParams[0];
        actualTypeParam = ((ParameterizedType) actualTypeParam).getRawType();
        Class<?> b = (Class)actualTypeParam;
        System.out.println(b.getName());


        for (int i = 0; i < typeParams.length; i++) {
            System.out.println(typeParams[i].getName());
            ;
        }

    }

    private static <T extends Enum<T> & Operation> void test(Class<T>
            extendedOperationClass, double x, double y) {
        for (Operation operation : extendedOperationClass.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, operation, y, operation.apply(x, y));
        }
    }

    public static class A<T> {

    }
}

