package com.zhou.mjava.consult.chatreport;

/**
 * @author liqingzhou on 18/1/16
 */
public class ReportOrder {
    private String resCode;
    private String msg;
    private OrderData data;


    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OrderData getData() {
        return data;
    }

    public void setData(OrderData data) {
        this.data = data;
    }
}
