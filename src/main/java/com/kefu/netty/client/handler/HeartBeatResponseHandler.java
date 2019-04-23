package com.kefu.netty.client.handler;

import com.kefu.netty.protocol.response.HeartBeatResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 心跳检测响应
 *
 * @author feng
 * @date 2019-04-21
 */
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket msg) throws Exception {
        System.out.println("心跳检测响应");
    }
}
