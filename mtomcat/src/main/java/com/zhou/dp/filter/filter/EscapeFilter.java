package com.zhou.dp.filter.filter;

import com.zhou.dp.filter.MyRequest;
import com.zhou.dp.filter.MyResponse;
import com.zhou.dp.filter.chain.FilterChain;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by liqingzhou on 17/7/30.
 */
public class EscapeFilter implements Filter {

    @Override
    public void doFilter(MyRequest request, MyResponse response, FilterChain filterChain) {
        String html = request.getContent();
        String escapeHtml4 = StringEscapeUtils.escapeHtml4(html);
        request.setContent(escapeHtml4);
        response.setContent(escapeHtml4);
        System.out.println("Do EscapeFilter: " + escapeHtml4);
        filterChain.doFilter(request, response);
    }
}
