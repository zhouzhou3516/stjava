package com.zhou.mjava.Test;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author liqingzhou on 18/9/19
 */
public class ShortUrl {

    public static void main(String[] args) {
        String url = "https://blog.mimvp.com/article/25420.html";
        String md5Hex = DigestUtils.md5Hex(url);
        String first = md5Hex.substring(0, 8);
        System.out.println(first);
        long l = Long.parseLong(first, 16);
        System.out.println(Long.toBinaryString(l));
        Long hexLong = 0x3FFFFFFF & l;
        System.out.println(Long.toBinaryString(hexLong));

        String string = Long.toBinaryString(Long.parseLong("FFFFFFFF", 16));
        System.out.println(string);
        // md5hex,32位（16进制），分4段,每段（4*8）
        // 取一段 4*8=32位
        // 30位
    }
}
