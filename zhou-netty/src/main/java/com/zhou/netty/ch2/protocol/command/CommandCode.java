package com.zhou.netty.ch2.protocol.command;

/**
 * @author liqingzhou on 18/10/12
 */
public interface CommandCode {

    byte LOGIN_REQUEST = 1;

    byte LOGIN_RESPONSE = 2;

    byte MESSAGE_REQUEST = 3;

    byte MESSAGE_RESPONSE = 4;

}
