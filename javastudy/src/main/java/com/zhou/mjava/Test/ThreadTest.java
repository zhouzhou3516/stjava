package com.zhou.mjava.Test;

import com.google.common.collect.Lists;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author liqingzhou on 18/4/9
 */
public class ThreadTest {

//    private static AsyncTaskService asyncTaskService = new AsyncTaskService("test", 6, 5000);

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        Type[] types = list.getClass().getGenericInterfaces();
        Type[] params = ((ParameterizedType) types[0]).getActualTypeArguments();
        Class reponseClass = (Class) params[0];
        System.out.println(reponseClass.getClass());
        Type[] actualTypeArguments = ((ParameterizedType) (list.getClass().getGenericInterfaces()
                [0]))
                .getActualTypeArguments();
        Class c = (Class) actualTypeArguments[0];
        System.out.println(c.getClass());

    }

}
