package com.zhou.mjava.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author liqingzhou on 18/5/10
 */
public class BigDecimalTest {
    public static int centOf(BigDecimal yuan) {
        return yuan.setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).intValue();
    }
    public static BigDecimal yunOf(BigDecimal cent){
        return cent.divide(new BigDecimal(100.0), 4, RoundingMode.HALF_EVEN);
    }
    public static BigDecimal yunOf1(BigDecimal cent){
        return cent.divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
    }
    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal("12345");
        System.out.println(yunOf(decimal));
        System.out.println(yunOf1(decimal));
    }

}
