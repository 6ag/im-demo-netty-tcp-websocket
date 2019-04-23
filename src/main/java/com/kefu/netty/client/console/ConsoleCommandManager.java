package com.kefu.netty.client.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @author feng
 * @date 2019-04-21
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("login", new LoginConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
        consoleCommandMap.put("sendToGroup", new SendToGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("----------------------------------");
        System.out.println("----登录:login");
        System.out.println("----退出:logout");
        System.out.println("----一对一发送消息:sendToUser");
        System.out.println("----创建群组:createGroup");
        System.out.println("----加入群组:joinGroup");
        System.out.println("----退出群组:quitGroup");
        System.out.println("----获取群组成员列表:listGroupMembers");
        System.out.println("----发群消息:sendToGroup");
        System.out.println("----------------------------------");
        System.out.println("请输入操作指令:");

        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("无法识别[" + command + "]指令，请重新输入!");
        }

    }
}
