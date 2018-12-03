package com.zhou.mjava.Test;

/**
 * @author liqingzhou on 18/7/21
 */
public class DataTypeTest {

    public static void main(String[] args) {
        String binaryString = Integer.toBinaryString(-2);
        /**
         * -2:11111111111111111111111111111110
         * 计算机负数用补码表示:
         * 1. 取正数的原码: 00000000000000000000000000000010
         * 2. 取反 11111111111111111111111111111101
         * 3. 加1 : 11***10
         * 补码特性：
         * 1.计算机只能算二进制加法。
         * 负数补码表示 + 一个正数 = 正确结果值
         * 2. 负数补码再取一次补码，就是他的正数部分的10进制
         */
        System.out.println(binaryString);
        System.out.println(binaryString.length());
        //16进制
        int hexInt = 0xA;
        int decimalInt = 10;
        int binaryInt = 0b1010;
//        System.out.println("hexInt=" + q7hnaryInt + binaryInt);
    }

}
