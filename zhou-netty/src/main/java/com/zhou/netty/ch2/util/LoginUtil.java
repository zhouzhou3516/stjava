package com.zhou.netty.ch2.util;


import com.zhou.netty.ch2.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author liqingzhou on 18/10/15
 */
public class LoginUtil {

    public static boolean hasLogin(Channel channel) {
        Attribute attribute = channel.attr(Attributes.LOGIN);
        return attribute.get() != null;
    }

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

}
