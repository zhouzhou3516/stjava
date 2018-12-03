package com.zhou.jdbc.abstractdemo.dynamicproxy;

import com.zhou.jdbc.abstractdemo.helperplus.resultSetHandler.AdvancedReflectionResultSetHandler;

/**
 * Created by liqingzhou on 17/8/9.
 */
public class Piece {

    private String sql;
    private String parameterClass;
    private String resultClazz;
    private AdvancedReflectionResultSetHandler handler;

    public void init() {
        try {
            this.handler = new AdvancedReflectionResultSetHandler(Class.forName(this.resultClazz));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParameterClass() {
        return parameterClass;
    }

    public void setParameterClass(String parameterClass) {
        this.parameterClass = parameterClass;
    }

    public String getResultClazz() {
        return resultClazz;
    }

    public void setResultClazz(String resultClazz) {
        this.resultClazz = resultClazz;
    }

    public AdvancedReflectionResultSetHandler getHandler() {
        return handler;
    }

    public void setHandler(
            AdvancedReflectionResultSetHandler handler) {
        this.handler = handler;
    }
}
