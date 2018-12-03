package com.zhou.mjava.sample.javautil;

import java.util.Random;

/**
 * Created by liqingzhou on 17/8/1.
 */
public class RandomSample {
    //产生位于0-n之间的随机整数

    //不好的实现
    private static final Random RANDOM = new Random();

    static int random(int n) {
        return Math.abs(RANDOM.nextInt()) % n;
    }

    public static void main(String[] args) {
        int n = 2*(Integer.MAX_VALUE/3);
        int low = 0;
        for (int i = 0; i < 1000000; i++) {
            if (random(n) < n / 2) {
                low++;
            }

        }
        System.out.println(low);



        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(-Integer.MIN_VALUE);


    }
}
