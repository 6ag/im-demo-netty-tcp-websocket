package com.kefu.netty.protocol.response;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.command.Command;
import com.kefu.netty.session.Session;

import java.util.List;

import lombok.Data;

/**
 * @author feng
 * @date 2019-04-21
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    /**
     * 生产环境中，这里可能会构造另外一个对象来装载用户信息而非 Session
     */
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
