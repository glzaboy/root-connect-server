package com.qintingfm.root.connect.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qintingfm.root.connect.server.pojo.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class UserDecoder extends MessageToMessageDecoder<ByteBuf> {
    ObjectMapper objectMapper;
    public UserDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        User user = objectMapper.readValue(msg.toString(io.netty.util.CharsetUtil.US_ASCII), User.class);
        out.add(user);
    }
}
