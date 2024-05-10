package com.grpc.Service.Config;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.grpc.Service.ChatStream;
import com.grpc.proto.Messaging.ChatMessage;
import com.grpc.proto.Messaging.User;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;

public class PubSubConfig {
    private  RedisClient redisClient = RedisConnectionHolder.Instance.getClient();
    private  StatefulRedisPubSubConnection<String, String> pubsubConnection ;
    private  static RedisPubSubAsyncCommands<String, String> async;

    public PubSubConfig(User user,ChatStream<ChatMessage> streamToSend){
         pubsubConnection = redisClient.connectPubSub();
         async= pubsubConnection.async();
         pubsubConnection.addListener(new  RedisPubSubListener<String,String>() {
            @Override
            public void message(String channel, String message) {
                System.out.println("Sending the Message");
                ChatMessage.Builder chatMessBuilder = ChatMessage.newBuilder(); 
                JSONConfig.fromJson(message, chatMessBuilder);
                ChatMessage chatMess = chatMessBuilder.build();
                System.out.println("Message : "+chatMess.toString());
                streamToSend.send(chatMess);
            }

            @Override
            public void message(String pattern, String channel, String message) {
            }

            @Override
            public void subscribed(String channel, long count) {
            }

            @Override
            public void psubscribed(String pattern, long count) {
            }

            @Override
            public void unsubscribed(String channel, long count) {
            }

            @Override
            public void punsubscribed(String pattern, long count) {
            }
         });
    }
    public void subscribeToChannel(String channelId){
        System.out.println("subscribed to channel "+channelId);
        async.subscribe(channelId);
    };

    public static void publishMessage(String channelId, ChatMessage message) {
        try {
            System.out.println("message published to channel "+channelId);
            async.publish(channelId,JSONConfig.toJson(message) );
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    public void closeConnection(){
        pubsubConnection.close();
    }
}
