package com.kefu.netty.handler;

import com.kefu.netty.protocol.request.CreateGroupRequestPacket;
import com.kefu.netty.protocol.response.CreateGroupResponsePack;
import com.kefu.netty.util.IDUtil;
import com.kefu.netty.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

/**
 * 创建群聊请求逻辑处理器
 *
 * @author feng
 * @date 2019-04-21
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {

        List<String> userIdList = msg.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (Channel channel : channelGroup) {
            System.out.println(channel.toString());
        }

        // 筛选出在线的用户
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                System.out.println("用户在线:创建群聊，邀请用户" + userId + "加入");
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            } else {
                System.out.println("用户不在线:创建群聊，邀请用户" + userId + "加入");
            }
        }

        // 创建群聊响应
        CreateGroupResponsePack createGroupResponsePack = new CreateGroupResponsePack();
        createGroupResponsePack.setSuccess(true);
        createGroupResponsePack.setGroupId(IDUtil.randomId());
        createGroupResponsePack.setUserNameList(userNameList);

        // 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePack);

        System.out.println("群创建成功，id 为[" + createGroupResponsePack.getGroupId() + "]，");
        System.out.println("群成员:" + createGroupResponsePack.getUserNameList());

        // 保存群组相关的信息
        SessionUtil.bindChannelGroup(createGroupResponsePack.getGroupId(), channelGroup);
    }
}
