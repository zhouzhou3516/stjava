package com.zhou.jdbc.abstractdemo.dynamicproxy;


import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuchao on 2017/7/22.
 */
public class ProxyRegister {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private JdbcInvocationHandler jdbcInvocationHandler = new JdbcInvocationHandler();

    public ProxyRegister(String configFile) {
        try {
            String content = Files.toString(new File(configFile), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public <T> T getInstance(Class clazz) {
        Object obj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz},
                jdbcInvocationHandler);
        return (T) obj;
    }
}
