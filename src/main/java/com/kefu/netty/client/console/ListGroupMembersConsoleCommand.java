package com.kefu.netty.client.console;

import com.kefu.netty.protocol.request.ListGroupMembersRequestPacket;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @author feng
 * @date 2019-04-21
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        System.out.println("输入 groupId，获取群成员列表:");
        String groupId = scanner.next();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
