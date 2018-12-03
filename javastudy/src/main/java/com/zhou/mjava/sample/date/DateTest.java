package com.zhou.mjava.sample.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liqingzhou on 17/6/14.
 */
public class DateTest {


    public void f1() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d天H小时mm分ss秒");
        Date d = new Date(2000);
        System.out.println(format.format(d));
    }
}
