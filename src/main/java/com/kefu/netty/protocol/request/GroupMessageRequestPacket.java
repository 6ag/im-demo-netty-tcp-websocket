package com.kefu.netty.protocol.request;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.command.Command;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author feng
 * @date 2019-04-21
 */
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
