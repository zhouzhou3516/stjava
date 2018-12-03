package com.zhou.mjava.sample.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liqingzhou on 18/9/20
 */
public class LogProxyHandler implements InvocationHandler {

    private UserService userService;

    public LogProxyHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before enter method:" + method.getName());
        // proxy 代理对象本身，用处不大
        System.out.println("proxy对象:" + proxy.getClass().getName());
        Object invoke = method.invoke(userService, args);
        System.out.println("after invode method:" + method.getName());
        return invoke;
    }
}
