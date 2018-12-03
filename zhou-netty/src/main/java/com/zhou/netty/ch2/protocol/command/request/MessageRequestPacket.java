package com.zhou.netty.ch2.protocol.command.request;

import com.zhou.netty.ch2.protocol.command.CommandCode;
import com.zhou.netty.ch2.protocol.Packet;

/**
 * @author liqingzhou on 18/10/15
 */
public class MessageRequestPacket extends Packet {

    private String from;
    private String to;
    private String message;

    @Override
    public Byte getCommand() {
        return CommandCode.MESSAGE_REQUEST;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
