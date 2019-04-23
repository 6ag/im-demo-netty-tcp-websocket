package com.kefu.netty.client.handler;

import com.kefu.netty.protocol.response.GroupMessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author feng
 * @date 2019-04-21
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        System.out.println("收到群[" + msg.getFromGroupId() + "]中[" + msg.getFromUser() + "]发来的消息：" + msg.getMessage());
    }
}
