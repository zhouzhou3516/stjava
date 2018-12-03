package com.zhou.jdbc.abstractdemo.dynamicproxy;

import com.zhou.jdbc.abstractdemo.helperplus.JdbcHelperPlus;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by liqingzhou on 17/8/9.
 */
public class JdbcInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String url = "jdbc:mysql://10.32.64.19:3306/training?characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true";
        String name = "beta";
        String password = "123456";
        JdbcHelperPlus plus = new JdbcHelperPlus(url, name, password);
        // get sql/handler
        return null;
    }
}
