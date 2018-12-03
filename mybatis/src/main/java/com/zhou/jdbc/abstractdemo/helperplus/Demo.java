package com.zhou.jdbc.abstractdemo.helperplus;

import com.zhou.jdbc.abstractdemo.helperplus.resultSetHandler.AdvancedReflectionResultSetHandler;
import com.zhou.jdbc.abstractdemo.helperplus.resultSetHandler.ResultSetHandler;
import com.zhou.jdbc.abstractdemo.helperplus.typeHandler.DateTypeHandler;
import com.zhou.jdbc.abstractdemo.helperplus.typeHandler.TypeHandler;
import java.util.Date;

/**
 * Created by liqingzhou on 17/8/8.
 */
public class Demo {

    public static void main(String[] args) {
        String url = "jdbc:mysql://10.32.64.19:3306/training?characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true";
        String name = "beta";
        String password = "123456";
        JdbcHelperPlus jdbcHelperPlus = new JdbcHelperPlus(url, name, password);



        //
        AdvancedReflectionResultSetHandler<Person> resultSetHandler = new AdvancedReflectionResultSetHandler(
                Person.class);
        TypeHandler<Date> typeHandler = new DateTypeHandler();

        resultSetHandler.register(Date.class,typeHandler);
        jdbcHelperPlus
                .query("select id,name,create_time from person where id = 1", resultSetHandler);
    }
}
