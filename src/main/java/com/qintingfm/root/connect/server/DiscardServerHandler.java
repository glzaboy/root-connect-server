package com.qintingfm.root.connect.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qintingfm.root.connect.server.pojo.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;

public class DiscardServerHandler extends ChannelHandlerAdapter {
    Logger logger= LoggerFactory.getLogger(DiscardServerHandler.class);
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)

        // Discard the received data silently.
        ByteBuf in = (ByteBuf) msg;
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(new String(in.toString(CharsetUtil.US_ASCII)), User.class);
            logger.info(user.toString());
            ByteBuf buffer = ctx.alloc().buffer(1024);
            user.setIp(new Date().toString());
            buffer.writeBytes(mapper.writeValueAsString(user).getBytes());
            ChannelFuture write = ctx.writeAndFlush(buffer);
            write.addListener(ChannelFutureListener.CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
            ctx.close();
        }
//        System.out.println(new String(in.toString(CharsetUtil.US_ASCII)));
//        System.out.flush();

//        ctx.flush();

//        ReferenceCountUtil.release(msg);

    }

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//        ByteBuf buffer = ctx.alloc().buffer(100);
//        InetSocketAddress socketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
//        buffer.writeBytes((socketAddress.getHostString()+socketAddress.getAddress()+socketAddress.getPort()).getBytes());
//        ChannelFuture write = ctx.writeAndFlush(buffer);
//        write.addListener(ChannelFutureListener.CLOSE);
////        write.addListener(new ChannelFutureListener() {
////            @Override
////            public void operationComplete(ChannelFuture channelFuture) throws Exception {
////                channelFuture.channel().close();
////            }
////        });
//
//    }
    //    @Override
//
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
//
//        // Close the connection when an exception is raised.
//
//        cause.printStackTrace();
//
//        ctx.close();
//
//    }
}
