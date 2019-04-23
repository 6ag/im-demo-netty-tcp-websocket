package com.kefu.netty.protocol.request;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.command.Command;

import lombok.Data;

/**
 * @author feng
 * @date 2019-04-21
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
