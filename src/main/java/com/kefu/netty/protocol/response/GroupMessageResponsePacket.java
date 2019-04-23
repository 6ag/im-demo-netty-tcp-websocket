package com.kefu.netty.protocol.response;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.command.Command;
import com.kefu.netty.session.Session;

import lombok.Data;

/**
 * @author feng
 * @date 2019-04-21
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
