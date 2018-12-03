package com.zhou.jdbc.abstractdemo.helperplus.resultSetHandler;

import com.zhou.jdbc.abstractdemo.helperplus.typeHandler.TypeHandler;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by liqingzhou on 17/8/8.
 */
public interface ResultSetHandler<T> {

    T handler(ResultSet resultSet);

}
