package com.kefu.netty.client.console;

import com.kefu.netty.protocol.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @author feng
 * @date 2019-04-21
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {

        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.println("【拉人群聊】输入userId列表，多个userId以英文,隔开:");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
