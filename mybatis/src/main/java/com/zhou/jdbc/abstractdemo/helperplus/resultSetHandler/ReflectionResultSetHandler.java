package com.zhou.jdbc.abstractdemo.helperplus.resultSetHandler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 第一步抽象出ResultSetHandler
 *
 * Created by liqingzhou on 17/8/8.
 */
public class ReflectionResultSetHandler<T> implements ResultSetHandler<T> {

    Logger logger = LoggerFactory.getLogger(getClass());
    private Class<T> clazz;

    public ReflectionResultSetHandler(Class<T> tClass) {
        clazz = tClass;
    }

    @Override
    public T handler(ResultSet resultSet) {
        try {
            T instance = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Object v = null;
                try {
                    //考虑 ：这里又有一个变化点：不同的数据库类型 =>java 类型的装换，厨房里比如 数据库的timestap类型怎么映射为 java.util.Date对象？
                    // 这就是下一步要抽点的点，参考 AdvancedReflectionResultSetHandler
                    v = resultSet.getObject(field.getName(), field.getType());
                    field.setAccessible(true);
                    field.set(instance, v);
                } catch (SQLException e) {
                    logger.error("resultSet error:{}", field.getName(), e);
                }

            }
            return instance;

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
