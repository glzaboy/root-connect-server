package com.qintingfm.root.connect.server;

import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationClose implements ApplicationListener<ContextClosedEvent> {
    Logger logger= LoggerFactory.getLogger(ApplicationClose.class);
    @Autowired
    @Qualifier("bossGroup")
    NioEventLoopGroup bossGroup;
    @Autowired
    @Qualifier("workGroup")
    NioEventLoopGroup workGroup;
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        logger.info("application close");
        logger.info("workGroup {},{}",workGroup,bossGroup);
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
