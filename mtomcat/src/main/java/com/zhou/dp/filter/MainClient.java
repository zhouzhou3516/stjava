package com.zhou.dp.filter;

import com.zhou.dp.filter.chain.MyFilterChain;
import com.zhou.dp.filter.filter.SensitiveFilter;
import com.zhou.dp.filter.filter.UnescapeFilter;

/**
 * Created by liqingzhou on 17/7/28.
 */
public class MainClient {

    public static void main(String[] args) {
        //html
        String content = "&lt;html&gt;&lt;body&gt;&lt;p&gt;习近平在恐怖天朝翻墙&lt;/p&gt;&lt;body&gt;&lt;/html&gt;";
        System.out.println("origin request:" + content);
        MyRequest request = new MyRequest();
        request.setContent(content);
        //处理结果
        MyResponse response = new MyResponse();
        MyFilterChain filterChain = new MyFilterChain();
        //设置过滤器
        //1. 替换敏感词
        filterChain.addFilter(new SensitiveFilter());
        //2. unescape html tag
        filterChain.addFilter(new UnescapeFilter());
        // 以此过滤规则
        filterChain.doFilter(request, response);
        // 打印结果
        System.out.println("final result: " + response.getContent());

    }
}
