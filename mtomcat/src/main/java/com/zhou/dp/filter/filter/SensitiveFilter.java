package com.zhou.dp.filter.filter;

import com.google.common.collect.Maps;
import com.zhou.dp.filter.MyRequest;
import com.zhou.dp.filter.MyResponse;
import com.zhou.dp.filter.chain.FilterChain;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by liqingzhou on 17/7/30.
 */
public class SensitiveFilter implements Filter {

    static Map<String, String> sensitiveMap = Maps.newHashMap();

    static {
        sensitiveMap.put("习近平", "习大大");
        sensitiveMap.put("翻墙", "科学上网");
        sensitiveMap.put("恐怖", "和平");
        sensitiveMap.put("天朝", "中国");
    }

    @Override
    public void doFilter(MyRequest request, MyResponse response, FilterChain filterChain) {
        String content = request.getContent();
        Iterator<Entry<String, String>> iter = sensitiveMap.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = iter.next();
            content = content.replaceAll(entry.getKey(), entry.getValue());
        }
        request.setContent(content);
        response.setContent(content);
        System.out.println("Do sensitiveFilter:"+content);
        filterChain.doFilter(request, response);

    }
}
