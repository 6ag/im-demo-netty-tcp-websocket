package com.kefu.netty.client;

import com.kefu.netty.client.console.ConsoleCommandManager;
import com.kefu.netty.client.handler.CreateGroupResponseHandler;
import com.kefu.netty.client.handler.GroupMessageResponseHandler;
import com.kefu.netty.client.handler.HeartBeatTimerHandler;
import com.kefu.netty.client.handler.JoinGroupResponseHandler;
import com.kefu.netty.client.handler.ListGroupMembersResponseHandler;
import com.kefu.netty.client.handler.LoginResponseHandler;
import com.kefu.netty.client.handler.MessageResponseHandler;
import com.kefu.netty.codec.TcpPacketCodec;
import com.kefu.netty.client.handler.LogoutResponseHandler;
import com.kefu.netty.codec.TcpSpliter;
import com.kefu.netty.handler.IMIdleStateHandler;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 引导Netty客户端
 *
 * @author feng
 * @date 2019-04-20
 */
public class TcpChatClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8888;

    public void start() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 指定线程模型
                .group(workerGroup)
                // 指定 I/O 类型为 NIO
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        // 处理粘包半包
                        ch.pipeline().addLast(new TcpSpliter());
                        // 指令数据包解码器
                        ch.pipeline().addLast(new TcpPacketCodec());
                        // 登录
                        ch.pipeline().addLast(new LoginResponseHandler());

                        // 退出登录
                        ch.pipeline().addLast(new LogoutResponseHandler());
                        // 单聊消息
                        ch.pipeline().addLast(new MessageResponseHandler());
                        // 创建群组
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        // 加入群组
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        // 获取群成员
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        // 群消息
                        ch.pipeline().addLast(new GroupMessageResponseHandler());

                        // 心跳检测响应:可以不用这个处理器
//                        ch.pipeline().addLast(new HeartBeatResponseHandler());

                        // 心跳定时器
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
                    }
                });

        this.connect(bootstrap, MAX_RETRY);
    }

    /**
     * 连接远程节点
     *
     * @param bootstrap 启动器
     * @param retry     剩余重试次数
     */
    private void connect(Bootstrap bootstrap, int retry) {
        bootstrap
                .connect(HOST, PORT)
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功");
                        startConsoleThread(future.channel());
                    } else {
                        // 第几次重连
                        int order = MAX_RETRY - retry + 1;
                        // 本次重连的间隔
                        int delay = 1 << order;
                        System.out.println(new Date() + ":连接失败，第" + order + "次重连");
                        bootstrap.config().group().schedule(() -> connect(bootstrap, retry - 1), delay, TimeUnit.SECONDS);
                    }
                });
    }

    /**
     * 开启控制台线程，进行指令操作
     *
     * @param channel 连接
     */
    private void startConsoleThread(Channel channel) {
        new Thread(() -> {

            ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
            while (!Thread.interrupted()) {
                Scanner scanner = new Scanner(System.in);
                consoleCommandManager.exec(scanner, channel);
            }
        }).start();
    }

    public static void main(String[] args) {
        new TcpChatClient().start();
    }

}
