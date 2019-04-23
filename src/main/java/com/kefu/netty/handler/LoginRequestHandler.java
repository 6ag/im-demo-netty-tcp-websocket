package com.kefu.netty.handler;

import com.kefu.netty.protocol.request.LoginRequestPacket;
import com.kefu.netty.protocol.response.LoginResponsePacket;
import com.kefu.netty.session.Session;
import com.kefu.netty.util.IDUtil;
import com.kefu.netty.util.SessionUtil;

import java.util.Date;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录请求逻辑处理器
 *
 * @author feng
 * @date 2019-04-21
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        // 处理登录请求数据包
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        loginResponsePacket.setUsername(msg.getUsername());

        // 登录校验
        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            // 随机生成userId，生产环境需要注册账号并生成userId，然后存储在数据库中
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + msg.getUsername() + "]登录成功");
            // 缓存用户会话信息和连接的映射关系
            SessionUtil.bindSession(new Session(userId, msg.getUsername()), ctx.channel());
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() + ":登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 清除用户会话信息和连接的映射关系
        SessionUtil.unBindSession(ctx.channel());
    }
}
