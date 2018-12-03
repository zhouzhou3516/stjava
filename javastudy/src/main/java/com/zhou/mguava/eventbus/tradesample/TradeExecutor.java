package com.zhou.mguava.eventbus.tradesample;


import com.google.common.eventbus.EventBus;
import java.util.Date;

/**
 * Created by liqingzhou on 17/8/14.
 */
public class TradeExecutor {
    EventBus eventBus;

    public TradeExecutor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void executeTrade(String account,String type,double amount){
        eventBus.post(processTrade(account,type,amount));
    }

    private TradeAccountEvent processTrade(String account,String type,double amount){
        return new TradeAccountEvent(amount, new Date(),type, account);
    }
}
