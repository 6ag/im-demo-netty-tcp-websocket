package com.kefu.netty.initializer;

import com.kefu.netty.codec.TcpSpliter;
import com.kefu.netty.codec.TcpPacketCodec;
import com.kefu.netty.handler.IMIdleStateHandler;
import com.kefu.netty.util.PipelineUtil;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * tcp连接初始化Channel，给Channel关联的pipeline添加handler
 *
 * @author feng
 * @date 2019-04-22
 */
@Component
public class TcpServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 空闲检测
        pipeline.addLast(new IMIdleStateHandler());
        // 处理粘包半包
        pipeline.addLast(new TcpSpliter());
        // 数据包编解码器
        pipeline.addLast(TcpPacketCodec.INSTANCE);

        // 添加tcp/websocket通用handler
        PipelineUtil.addHandler(pipeline);

    }
}
