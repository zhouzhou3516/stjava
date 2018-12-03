package com.zhou.dp.filter.chain;

import com.google.common.collect.Lists;
import com.zhou.dp.filter.MyRequest;
import com.zhou.dp.filter.MyResponse;
import com.zhou.dp.filter.filter.Filter;
import java.util.List;

/**
 * Created by liqingzhou on 17/7/28.
 */
public class MyFilterChain implements FilterChain {

    List<Filter> filterList = Lists.newArrayList();
    int index = 0;

    @Override
    public void doFilter(MyRequest request, MyResponse response) {
        if (index >= filterList.size()) {
            return;
        }
        Filter filter = filterList.get(index);
        index++;
        filter.doFilter(request, response, this);
    }

    public FilterChain addFilter(Filter filter) {
        filterList.add(filter);
        return this;
    }
}
