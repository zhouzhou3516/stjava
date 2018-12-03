package com.zhou.dp.filter.chain;

import com.zhou.dp.filter.MyRequest;
import com.zhou.dp.filter.MyResponse;
import com.zhou.dp.filter.filter.Filter;

/**
 * Created by liqingzhou on 17/7/28.
 */
public interface FilterChain {

    void doFilter(MyRequest request, MyResponse response);
    FilterChain addFilter(Filter filter);
}
