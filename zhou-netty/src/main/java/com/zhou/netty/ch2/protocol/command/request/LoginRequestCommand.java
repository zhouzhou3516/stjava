package com.zhou.netty.ch2.protocol.command.request;

import com.zhou.netty.ch2.protocol.command.CommandCode;
import com.zhou.netty.ch2.protocol.Packet;

/**
 * @author liqingzhou on 18/10/12
 */
public class LoginRequestCommand extends Packet {

    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return CommandCode.LOGIN_REQUEST;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
