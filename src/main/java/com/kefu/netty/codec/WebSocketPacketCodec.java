package com.kefu.netty.codec;

import com.kefu.netty.protocol.Packet;
import com.kefu.netty.protocol.PacketCodeC;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;

/**
 * WebSocket数据包编解码器
 *
 * @author feng
 * @date 2019-04-21
 */
@ChannelHandler.Sharable
public class WebSocketPacketCodec extends MessageToMessageCodec<WebSocketFrame, Packet> {

    public static final WebSocketPacketCodec INSTANCE = new WebSocketPacketCodec();

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(byteBuf, msg);
        out.add(new BinaryWebSocketFrame(byteBuf));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(msg.content()));
    }

}
