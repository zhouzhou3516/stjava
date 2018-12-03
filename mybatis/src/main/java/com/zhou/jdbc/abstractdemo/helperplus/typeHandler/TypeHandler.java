package com.zhou.jdbc.abstractdemo.helperplus.typeHandler;

import java.sql.ResultSet;

/**
 * Created by liqingzhou on 17/8/8.
 */
public interface TypeHandler<T> {

    T handler(String filedName, ResultSet resultSet);
}
