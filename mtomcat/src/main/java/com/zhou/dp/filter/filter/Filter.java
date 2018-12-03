package com.zhou.dp.filter.filter;

import com.zhou.dp.filter.chain.FilterChain;
import com.zhou.dp.filter.MyRequest;
import com.zhou.dp.filter.MyResponse;

/**
 * Created by liqingzhou on 17/7/28.
 */
public interface Filter {

    void doFilter(MyRequest request, MyResponse response, FilterChain filterChain);
}
