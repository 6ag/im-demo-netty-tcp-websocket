package com.kefu.netty.protocol.response;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.command.Command;

import lombok.Data;

/**
 * @author feng
 * @date 2019-04-21
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
