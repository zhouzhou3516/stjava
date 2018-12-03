package com.zhou.mguava.eventbus.tradesample;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by liqingzhou on 17/8/14.
 */
public class TradeAuditor {

    public TradeAuditor(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void auditTrade(TradeAccountEvent tradeAccountEvent){
        System.out.println("tradeAccountEvent = " + tradeAccountEvent);
    }
}
