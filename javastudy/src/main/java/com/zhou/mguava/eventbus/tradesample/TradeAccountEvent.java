package com.zhou.mguava.eventbus.tradesample;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by liqingzhou on 17/8/14.
 */
public class TradeAccountEvent implements Serializable {

    private double amount;
    private Date tradeExecutionTime;
    private String tradeType;
    private String tradeAccount;

    public TradeAccountEvent(double amount, Date tradeExecutionTime, String tradeType,
            String tradeAccount) {
        this.amount = amount;
        this.tradeExecutionTime = tradeExecutionTime;
        this.tradeType = tradeType;
        this.tradeAccount = tradeAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTradeExecutionTime() {
        return tradeExecutionTime;
    }

    public void setTradeExecutionTime(Date tradeExecutionTime) {
        this.tradeExecutionTime = tradeExecutionTime;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeAccount() {
        return tradeAccount;
    }

    public void setTradeAccount(String tradeAccount) {
        this.tradeAccount = tradeAccount;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
