package com.zhou.mjava.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 短链接
 *
 * @author liqingzhou on 18/9/20
 */
public class ShortLink {

    private static final String ALPHBET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LEN = ALPHBET.length();
    private static AtomicInteger idIncr = new AtomicInteger(0);

    public static void main(String[] args) {
        String longUrl = "http://www.googole.com";
        int id = saveUrl(longUrl);
        String encode = encode(id);
        System.out.println(encode);

    }

    public static int saveUrl(String longUrl) {
        // todo save to db
        idIncr.incrementAndGet();
        return Integer.MAX_VALUE;
    }

    public static String encode(int num) {
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            builder.append(ALPHBET.charAt(num % LEN));
            num /= LEN;
        }
        return builder.toString();
    }
}
