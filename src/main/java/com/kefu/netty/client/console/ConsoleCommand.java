package com.kefu.netty.client.console;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * 控制台指令根接口
 *
 * @author feng
 * @date 2019-04-21
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
