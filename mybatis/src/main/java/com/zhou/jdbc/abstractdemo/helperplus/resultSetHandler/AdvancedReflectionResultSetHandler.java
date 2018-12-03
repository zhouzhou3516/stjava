package com.zhou.jdbc.abstractdemo.helperplus.resultSetHandler;

import com.google.common.collect.Maps;
import com.zhou.jdbc.abstractdemo.helperplus.typeHandler.TypeHandler;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这一步在ResultSetHandler基础上进行第二部抽象：抽象出typeHandler
 *
 * Created by liqingzhou on 17/8/8.
 */
public class AdvancedReflectionResultSetHandler<T> implements ResultSetHandler<T> {

    public Map<Class, TypeHandler> typeHandlerHolder = Maps.newHashMap();
    Logger logger = LoggerFactory.getLogger(getClass());
    private Class<T> clazz;

    public AdvancedReflectionResultSetHandler(Class<T> tClass) {
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
                    // 这里又有一个变化点：数据库类型 => java类型的装换，比如 数据库的 timestap类型 映射为 java.util.Date对象？
                    TypeHandler typeHandler = typeHandlerHolder.get(field.getType());
                    if (typeHandler != null) {
                        v = typeHandler.handler(field.getName(), resultSet);
                    } else {

                        v = resultSet.getObject(field.getName(), field.getType());
                    }
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

    public void register(Class clazz,TypeHandler typeHandler) {
        typeHandlerHolder.put(clazz,typeHandler);
    }
}
