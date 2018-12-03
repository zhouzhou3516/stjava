package com.zhou.mjava.sample.proxy;

import java.lang.reflect.Proxy;

/**
 * @author liqingzhou on 18/9/20
 */
public class ProxyTest {

    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        UserService proxy = (UserService)Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                new Class<?>[]{UserService.class},
                new LogProxyHandler(service));
        proxy.getUser();
    }

}
