package com.qintingfm.root.connect.server.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyConfig {
    Logger logger= LoggerFactory.getLogger(NettyConfig.class);

    @Bean("bossGroup")
    public NioEventLoopGroup bossGroup(){
        logger.info("register bossGroup");
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        return nioEventLoopGroup;
    }
    @Bean("workGroup")
    public NioEventLoopGroup workGroup(){
        logger.info("register workGroup");
        return new NioEventLoopGroup();
    }
    @Bean
    public ServerBootstrap serverBootstrap(){
        logger.info("register ServerBootstrap");
        ServerBootstrap serverBootstrap=new ServerBootstrap();

        return serverBootstrap;
    }

}
