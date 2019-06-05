package com.qintingfm.root.connect.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

public class DiscardServerHandler extends ChannelHandlerAdapter {
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//
//        // Discard the received data silently.
//        ByteBuf in = (ByteBuf) msg;
//        System.out.println(new String(in.toString(CharsetUtil.US_ASCII)));
//        System.out.flush();
//        ctx.write(msg);
//        ctx.flush();
////        ReferenceCountUtil.release(msg);
//
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ByteBuf buffer = ctx.alloc().buffer(100);
        buffer.writeBytes(new Date().toString().getBytes());
        ChannelFuture write = ctx.writeAndFlush(buffer);
        write.addListener(ChannelFutureListener.CLOSE);
//        write.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                channelFuture.channel().close();
//            }
//        });

    }
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
