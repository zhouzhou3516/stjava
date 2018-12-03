package com.zhou.netty.ch2.protocol.command.response;

import com.zhou.netty.ch2.protocol.command.CommandCode;
import com.zhou.netty.ch2.protocol.Packet;

/**
 * @author liqingzhou on 18/10/15
 */
public class LoginResponsePacket extends Packet {

    private int status;//0 ok
    private String message;

    @Override
    public Byte getCommand() {
        return CommandCode.LOGIN_RESPONSE;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
