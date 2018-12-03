package com.zhou.netty.ch2.server;

import com.zhou.netty.ch2.protocol.PacketCodeC;
import com.zhou.netty.ch2.protocol.command.request.LoginRequestCommand;
import com.zhou.netty.ch2.protocol.command.response.LoginResponsePacket;
import com.zhou.netty.ch2.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;

/**
 * @author liqingzhou on 18/9/30
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestCommand> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
            LoginRequestCommand loginRequestCommand) throws Exception {
        System.out.println(new Date() + ":客户端开始登录....");

        //登录流程
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestCommand.getVersion());
        if (valid(loginRequestCommand)) {
            loginResponsePacket.setStatus(0);
            loginResponsePacket.setMessage("");
            LoginUtil.markAsLogin(channelHandlerContext.channel());
            System.out.println(new Date() + ":" + loginRequestCommand.getUserName() + "登录成功");
        } else {
            loginResponsePacket.setStatus(101);
            loginResponsePacket.setMessage("账号密码校验失败");
            System.out.println(new Date() + ":" + loginRequestCommand.getUserName() + "登录失败");
        }
        ByteBuf out = PacketCodeC.encode(loginResponsePacket);
        channelHandlerContext.channel().writeAndFlush(out);
    }


    private boolean valid(LoginRequestCommand loginRequestCommand) {

        return true;
    }

}
