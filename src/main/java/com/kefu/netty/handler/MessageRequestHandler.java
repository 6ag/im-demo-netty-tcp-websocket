package com.kefu.netty.handler;

import com.kefu.netty.protocol.request.MessageRequestPacket;
import com.kefu.netty.protocol.response.MessageResponsePacket;
import com.kefu.netty.session.Session;
import com.kefu.netty.util.SessionUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息转发请求逻辑处理器
 *
 * @author feng
 * @date 2019-04-21
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {

        // 拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 构造信息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(msg.getMessage());

        // 拿到消息接收方的 Channel
        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

        // 将消息发给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.out.println("[" + msg.getToUserId() + "]不在线，发送失败!");
        }
    }
}
