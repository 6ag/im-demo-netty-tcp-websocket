package com.kefu.netty.protocol.response;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.command.Command;

import lombok.Data;

/**
 * 登录响应数据包
 *
 * @author feng
 * @date 2019-04-20
 */
@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String username;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
