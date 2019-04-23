package com.kefu.netty.protocol.request;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.command.Command;

import lombok.Data;

/**
 * 登录请求数据包
 *
 * @author feng
 * @date 2019-04-20
 */
@Data
public class LoginRequestPacket extends Packet {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

}
