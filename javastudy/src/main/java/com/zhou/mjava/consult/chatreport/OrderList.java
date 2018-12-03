package com.zhou.mjava.consult.chatreport;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author liqingzhou on 18/1/16
 */
public class OrderList {

    private List<Order> hits = Lists.newArrayList();

    public List<Order> getHits() {
        return hits;
    }

    public void setHits(List<Order> hits) {
        this.hits = hits;
    }
}
