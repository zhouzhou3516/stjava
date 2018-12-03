package com.zhou.mjava.sample.javautil;

import com.zhou.mjava.sample.polymorphism.Test;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @author liqingzhou on 18/10/18
 */
public class ClassUtil {

    public static void main(String[] args) {
//        TestD testD = new TestD();
        TestC<Integer> testC = new TestC<Integer>();
        TypeVariable<? extends Class<? extends TestC>>[] typeParameters = testC.getClass()
                .getTypeParameters();

        TypeVariable<? extends Class<? extends TestC>> typeParameter = typeParameters[0];
//        Class superClassGenricType = getSuperClassGenricType(testD.getClass());
        Class superClassGenricTypeC = getSuperClassGenricType(testC.getClass());
//        System.out.println(superClassGenricType.getName());
        System.out.println(superClassGenricTypeC.getName());
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */
    public static Class getSuperClassGenricType(Class clazz, int index)
            throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();


        if (!(genType instanceof ParameterizedType)) {
            System.out.println("object");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
