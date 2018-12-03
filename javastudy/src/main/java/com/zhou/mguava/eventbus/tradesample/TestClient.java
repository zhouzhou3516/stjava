package com.zhou.mguava.eventbus.tradesample;

import com.google.common.eventbus.EventBus;

/**
 * Created by liqingzhou on 17/8/14.
 */
public class TestClient {

    public static void main(String[] args) {
        EventBus eventBus =new EventBus();
        TradeExecutor executor = new TradeExecutor(eventBus);
        new TradeAuditor(eventBus);
        executor.executeTrade("lizz","car",1000.00);

    }
}
