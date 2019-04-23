package com.kefu.netty.client.handler;

import com.kefu.netty.protocol.response.MessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 接收消息
 *
 * @author feng
 * @date 2019-04-21
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(msg.getFromUserId() + ":" + msg.getFromUserName() + " -> " + msg.getMessage());
    }
}
