package com.qintingfm.root.connect.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStart implements ApplicationRunner {
    @Autowired
    ServerBootstrap serverBootstrap;
    @Autowired
    @Qualifier("bossGroup")
    NioEventLoopGroup bossGroup;
    @Autowired
    @Qualifier("workGroup")
    NioEventLoopGroup workGroup;
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        serverBootstrap.group(bossGroup,workGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true).option(ChannelOption.SO_BACKLOG,128);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new JsonObjectDecoder());
                socketChannel.pipeline().addLast(new DiscardServerHandler(objectMapper));
//                socketChannel.pipeline().addLast(new ReadTimeoutHandler(5));
            }
        });
        ChannelFuture sync = serverBootstrap.bind(9090).sync();
        sync.channel().closeFuture().sync();
    }

}
