package com.kefu.netty;

import com.kefu.netty.server.TcpChatServer;
import com.kefu.netty.server.WebSocketChatServer;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * NettyServer启动器
 *
 * @author feng
 * @date 2019-04-22
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    private final WebSocketChatServer webSocketChatServer;

    private final TcpChatServer tcpChatServer;

    public NettyBooter(WebSocketChatServer webSocketChatServer, TcpChatServer tcpChatServer) {
        this.webSocketChatServer = webSocketChatServer;
        this.tcpChatServer = tcpChatServer;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            webSocketChatServer.start();
//            tcpChatServer.start();
        }
    }
}
