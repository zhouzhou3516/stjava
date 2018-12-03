package com.zhou.netty.ch2.attribute;

import io.netty.util.AttributeKey;

/**
 * @author liqingzhou on 18/10/15
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = new AttributeKey("login");

}
