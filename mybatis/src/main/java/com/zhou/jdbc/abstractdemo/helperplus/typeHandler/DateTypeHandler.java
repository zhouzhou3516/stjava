package com.zhou.jdbc.abstractdemo.helperplus.typeHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liqingzhou on 17/8/8.
 */
public class DateTypeHandler implements TypeHandler<Date> {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Date handler(String fileName, ResultSet resultSet) {
        try {
            Timestamp object = resultSet.getTimestamp(fileName);
            return new Date(object.getTime());
        } catch (SQLException e) {
            logger.error("dateTypeHandler error  {}", fileName, e);
            throw new RuntimeException(e);
        }
    }
}
