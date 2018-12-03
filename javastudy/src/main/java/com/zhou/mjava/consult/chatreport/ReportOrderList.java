package com.zhou.mjava.consult.chatreport;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author liqingzhou on 18/1/16
 */
public class ReportOrderList {

    List<ReportOrder> list = Lists.newArrayList();


    public List<ReportOrder> getList() {
        return list;
    }

    public void setList(List<ReportOrder> list) {
        this.list = list;
    }
}
