package com.kefu.netty.handler;

import com.kefu.netty.protocol.request.JoinGroupRequestPacket;
import com.kefu.netty.protocol.response.JoinGroupResponsePacket;
import com.kefu.netty.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author feng
 * @date 2019-04-21
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {

        // 获取群对应的 channelGroup ，然后将当前用户的 channel 添加进去
        String groupId = msg.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        // 这里要再梳理一下逻辑：创建群组的时候已经添加了所有成员的连接，这里又添加连接，是不是重复了？
        channelGroup.add(ctx.channel());

        // 构造加群响应发送给客户端
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupId(groupId);
        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
