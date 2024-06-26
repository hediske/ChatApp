package com.grpc.Service.Channel;


import com.grpc.protoCompiled.Messaging.ChannelChat;
import com.grpc.protoCompiled.Messaging.Channel_STATUS;
import com.grpc.protoCompiled.Messaging.Channel_type;

public class ChannelFactImpl implements ChannelFactInt {

    @Override
    public ChannelChat createChannel(String channelIdp, Channel_STATUS status, Channel_type channel_type) {
        return ChannelChat
        .newBuilder()
        .setIdChannel(channelIdp)
        .setStatusChannel(status)
        .setTypeChannel(channel_type)
        .build();
    }

}
