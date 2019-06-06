package com.qintingfm.root.connect.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qintingfm.root.connect.server.pojo.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class UserEncoder extends MessageToByteEncoder<User> {
    ObjectMapper objectMapper;
    public UserEncoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    protected void encode(ChannelHandlerContext ctx, User msg, ByteBuf out) throws Exception {
        byte[] bytes = objectMapper.writeValueAsBytes(msg);
//        ByteBuf buffer = ctx.alloc().buffer(bytes.length);
//        buffer.writeBytes(bytes);
        out.writeBytes(bytes);
    }
}
