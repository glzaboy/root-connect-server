package com.qintingfm.root.connect.server;

import com.qintingfm.root.connect.server.pojo.User;
import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class DiscardServerHandler extends ChannelHandlerAdapter {
    Logger logger= LoggerFactory.getLogger(DiscardServerHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        User msg1 = (User) msg;
        msg1.setDate(new Date());
        ChannelFuture channelFuture = ctx.writeAndFlush(msg1);
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }
}
