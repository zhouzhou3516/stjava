package com.zhou.netty.ch2.protocol;

/**
 * @author liqingzhou on 18/10/12
 */
public abstract class Packet {

    /**
     * 协议版本
     */
    private byte version;

    /**
     * 指令
     */
    public abstract Byte getCommand();

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }
}
