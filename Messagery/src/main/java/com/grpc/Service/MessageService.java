package com.grpc.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.protobuf.Timestamp;
import com.grpc.Exception.NonExistantException;
import com.grpc.Service.Config.PubSubConfig;
import com.grpc.protoCompiled.Messaging.ChannelChat;
import com.grpc.protoCompiled.Messaging.ChatMessage;
import com.grpc.protoCompiled.Messaging.User;

import io.grpc.Channel;



public class MessageService {
    
    private PubSubMessageService messageReceival;
    public static Timestamp getCurrenTimestamp(){
        Instant time = Instant.now();
        return Timestamp.newBuilder()
                        .setSeconds(time.getEpochSecond())
                        .setNanos(time.getNano())
                        .build();
        
    }

    public static ChatMessage createMessage(User sender,  String content){
        return ChatMessage.newBuilder()
                .setMessageId(UUID.randomUUID().toString())
                .setSender(sender.getId())
                .setMsg(content)
                .setTime(getCurrenTimestamp())
                .build();
    }



    void sendMessage(User sender , ChannelChat channel , String content){
        ChatMessage message = createMessage(sender, content);
        PubSubConfig.publishMessage(channel.getIdChannel(), message);

    }


    void sendMessage(User sender , ChannelChat channel , ChatMessage content){
        PubSubConfig.publishMessage(channel.getIdChannel(), content);
    }

 

    void ReceiveMessage(User receiver , ChannelChat channel ,ChatStream<ChatMessage> stream ) throws NonExistantException {
            messageReceival = new PubSubMessageService(receiver, channel, new ChatStream<ChatMessage>(){
                @Override
                public void send(ChatMessage message) {
                    stream.send(message);
                }
            } );
            messageReceival.startReceiving();
        }
    
    public void StopReceiveMessage(User user , ChannelChat channel) {
           if(messageReceival!=null) 
                messageReceival.stopReceiving();         
    }

}
