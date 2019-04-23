package com.kefu.netty.protocol.request;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.command.Command;

import java.util.List;

import lombok.Data;

/**
 * 创建群组请求数据包
 *
 * @author feng
 * @date 2019-04-21
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
