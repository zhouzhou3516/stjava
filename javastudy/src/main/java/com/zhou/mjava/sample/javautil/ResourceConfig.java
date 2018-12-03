package com.zhou.mjava.sample.javautil;

//import com.benmu.agile.Closer;
import com.benmu.agile.Numbers;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @author liqingzhou on 18/3/5
 */
public class ResourceConfig {

    private final Map<String, String> data;

    public static ResourceConfig getOrNull(final String name) {
        try {
            return new ResourceConfig(name);
        } catch (Exception e) {
            return null;
        }
    }

    private ResourceConfig(final String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "配置文件名不能为空");

        final InputStream res = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        if (res == null) {
            throw new RuntimeException("无法找到配置文件: " + name);
        }

        try {
            final Properties prop = new Properties();
            prop.load(res);
            data = fromProperties(prop);
        } catch (Exception e) {
            throw new RuntimeException("无法读取配置文件：" + name, e);
        } finally {
//            Closer.close(res);
        }
    }

    private Map<String, String> fromProperties(final Properties prop) {
        final Map<String, String> map = Maps.newHashMap();
        for (final String key : prop.stringPropertyNames()) {
            map.put(key, prop.getProperty(key));
        }
        return map;
    }

    public String getString(final String key) {
        return data.get(key);
    }

    public int getInt(final String key, final int defaultValue) {
        return Numbers.toInt(data.get(key), defaultValue);
    }

    public Map<String, String> getAll() {
        return Collections.unmodifiableMap(data);
    }
}
