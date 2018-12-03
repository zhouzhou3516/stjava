package com.zhou.jdbc.abstractdemo.dynamicproxy;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhou.jdbc.abstractdemo.helperplus.resultSetHandler.AdvancedReflectionResultSetHandler;
import com.zhou.jdbc.abstractdemo.helperplus.typeHandler.TypeHandler;
import java.util.List;
import java.util.Map;

/**
 * @author zhuchao on 2017/7/25.
 */
public class Config {

    private Map<String, String> handlerTypeMap = Maps.newHashMap();
    private Map<String, Piece> sqlMap = Maps.newHashMap();
    private List<String> pluginList = Lists.newArrayList();

    public Map<String, String> getHandlerTypeMap() {
        return handlerTypeMap;
    }

    public void setHandlerTypeMap(Map<String, String> handlerTypeMap) {
        this.handlerTypeMap = handlerTypeMap;
    }

    public Map<String, Piece> getSqlMap() {
        return sqlMap;
    }

    public void setSqlMap(
            Map<String, Piece> sqlMap) {
        this.sqlMap = sqlMap;
    }

    public void init() {
        sqlMap.entrySet().forEach(entry -> {
            Piece piece = entry.getValue();
            piece.init();
            AdvancedReflectionResultSetHandler handler = piece.getHandler();
            handlerTypeMap.entrySet().forEach(typeEntry -> {
                try {
                    handler.register(Class.forName(typeEntry.getKey()),
                            (TypeHandler) Class.forName(typeEntry.getValue()).newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }
}
