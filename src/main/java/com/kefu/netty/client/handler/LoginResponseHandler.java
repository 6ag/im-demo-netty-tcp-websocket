package com.kefu.netty.client.handler;

import com.kefu.netty.protocol.response.LoginResponsePacket;
import com.kefu.netty.session.Session;
import com.kefu.netty.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author feng
 * @date 2019-04-21
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("[" + msg.getUsername() + "]登录成功，userId 为:" + msg.getUserId());
            SessionUtil.bindSession(new Session(msg.getUserId(), msg.getUsername()), ctx.channel());
        } else {
            System.out.println("[" + msg.getUsername() + "]登录失败，原因:" + msg.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
        // 清除用户会话信息和连接的映射关系
        SessionUtil.unBindSession(ctx.channel());
    }
}
