package com.kefu.netty.handler;

import com.kefu.netty.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 鉴权逻辑处理器
 *
 * @author feng
 * @date 2019-04-21
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // pipeline的热拔插，移除handler
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())) {
//            System.out.println("当前连接登录验证完毕，无需再次验证，AuthHandler 被移除");
        } else {
//            System.out.println("无登录验证，强制关闭连接！");
        }
    }
}
