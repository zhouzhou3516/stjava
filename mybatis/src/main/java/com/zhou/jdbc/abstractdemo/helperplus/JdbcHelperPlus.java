package com.zhou.jdbc.abstractdemo.helperplus;

import com.google.common.collect.Lists;
import com.zhou.jdbc.abstractdemo.helperplus.resultSetHandler.ResultSetHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liqingzhou on 17/8/8.
 */
public class JdbcHelperPlus {

    Logger logger = LoggerFactory.getLogger(getClass());
    private String username;
    private String password;
    private String url;

    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("load driver error:com.mysql.jdbc.Driver", e);
        }

    }

    public JdbcHelperPlus(String url, String username, String password) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public <T> T query(String sql, ResultSetHandler<T> resultSetHandler) {
        Connection connection;
        Statement statement;
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                return resultSetHandler.handler(resultSet);
            }
        } catch (SQLException e) {
            logger.error("query error", e);
        }
        return null;

    }

    public <T> Collection<T> queryObjects(String sql, ResultSetHandler<T> resultSetHandler) {
        Connection connection;
        Statement statement;
        List<T> retList = Lists.newArrayList();
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                retList.add(resultSetHandler.handler(resultSet));
            }
        } catch (SQLException e) {
            logger.error("query error", e);
        }
        return retList;
    }
}
