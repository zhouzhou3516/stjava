package com.zhou.netty.ch2.protocol.command.response;

import com.zhou.netty.ch2.protocol.command.CommandCode;
import com.zhou.netty.ch2.protocol.Packet;

/**
 * @author liqingzhou on 18/10/15
 */
public class MessageResponsePacket extends Packet {

    private int status;
    private String message;

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

    @Override
    public Byte getCommand() {
        return CommandCode.MESSAGE_RESPONSE;
    }
}
