package com.zhou.dp.filter.filter;

import com.zhou.dp.filter.MyRequest;
import com.zhou.dp.filter.MyResponse;
import com.zhou.dp.filter.chain.FilterChain;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by liqingzhou on 17/7/30.
 */
public class UnescapeFilter implements Filter {

    @Override
    public void doFilter(MyRequest request, MyResponse response, FilterChain filterChain) {
        String html = request.getContent();
        String unescapeHtml4 = StringEscapeUtils.unescapeHtml4(html);
        response.setContent(unescapeHtml4);
        response.setContent(unescapeHtml4);
        System.out.println("Do UnescapeFilter: "+unescapeHtml4);
        filterChain.doFilter(request, response);

    }
}
