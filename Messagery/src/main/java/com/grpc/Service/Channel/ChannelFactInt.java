package com.grpc.Service.Channel;


import com.grpc.protoCompiled.Messaging.ChannelChat;
import com.grpc.protoCompiled.Messaging.Channel_STATUS;
import com.grpc.protoCompiled.Messaging.Channel_type;

public interface ChannelFactInt {
    ChannelChat createChannel(String channelIdp,Channel_STATUS status,Channel_type channel_type );
}
