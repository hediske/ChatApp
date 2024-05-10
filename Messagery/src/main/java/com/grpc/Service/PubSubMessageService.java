package com.grpc.Service;

import com.grpc.Exception.NonExistantException;
import com.grpc.Service.Config.PubSubConfig;
import com.grpc.protoCompiled.Messaging.ChannelChat;
import com.grpc.protoCompiled.Messaging.ChatMessage;
import com.grpc.protoCompiled.Messaging.User;


public class PubSubMessageService {

    private final ChannelChat channel;
    private PubSubConfig pubsubconfig ;
    
    public PubSubMessageService(User receiver, ChannelChat channel, ChatStream<ChatMessage> stream) {
        this.channel = channel;
        if(stream!=null){
            System.out.println("++++++mechia shiha+++++++");
        }
        pubsubconfig= new PubSubConfig(receiver,stream);   
    }

    public void startReceiving() throws NonExistantException {
        String channelId = channel.getIdChannel();
        pubsubconfig.subscribeToChannel(channelId);
    }
    

    public void stopReceiving() {
        pubsubconfig.closeConnection();
    } 



    public PubSubConfig getPubSub(){
        return pubsubconfig;
    }
}



