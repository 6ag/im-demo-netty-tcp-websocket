package com.kefu.netty.client.console;

import com.kefu.netty.protocol.request.GroupMessageRequestPacket;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @author feng
 * @date 2019-04-21
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("发送信息到群组，请输入群组groupId:");
        String toGroupId = scanner.next();
        System.out.println("请输入消息内容:");
        String message = scanner.next();

        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
