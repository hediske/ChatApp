package com.grpc.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.reactivestreams.Subscription;

import com.grpc.Exception.AlreadyExistsException;
import com.grpc.Exception.NoRightGivenException;
import com.grpc.Exception.NonExistantException;
import com.grpc.Service.Channel.ChannelFactImpl;
import com.grpc.Service.Config.PubSubConfig;
import com.grpc.Service.Config.RedisConnectionHolder;
import com.grpc.Service.Config.JSONConfig;
import com.grpc.protoCompiled.Messaging.ChannelChat;
import com.grpc.protoCompiled.Messaging.Channel_STATUS;
import com.grpc.protoCompiled.Messaging.Channel_type;
import com.grpc.protoCompiled.Messaging.ChatMessage;
import com.grpc.protoCompiled.Messaging.User;

import io.lettuce.core.api.async.RedisAsyncCommands;


public class channelservice {
    private final RedisAsyncCommands<String, String>  redisCommands = RedisConnectionHolder.Instance.getConnectionCommands();
    private final ChannelFactImpl FactChannel ;
    private static final String CHANNELS_KEYS = "CHANNEL_KEYS:";
    private static final String CHANNELS_KEY_PREFIX = "channel:";
    private static final String MESSAGES_KEY_PREFIX = "messages:";
    private static final String MEMBERS_KEY_PREFIX = "members:";
    private static final String CONNECTED_MEMBERS_KEY_PREFIX = "connected:";


    public channelservice(){
        this.FactChannel= new ChannelFactImpl();
        
    }

    public ChannelChat createChannel(String channelIdp, Channel_STATUS status, Channel_type channel_type){
        return this.FactChannel.createChannel(channelIdp, status, channel_type);
    }   

    public void addChannel(ChannelChat channel, List<User> members) throws Exception{
       if(!redisCommands.sismember(CHANNELS_KEYS,channel.getIdChannel()).get())// not exsiting before
            {

                redisCommands.sadd("CHANNEL_KEYS", channel.getIdChannel());
                redisCommands.hset(CHANNELS_KEY_PREFIX+channel.getIdChannel(), "data" , JSONConfig.toJson(channel));
                for (User member : members) {
                    redisCommands.sadd(MEMBERS_KEY_PREFIX+channel.getIdChannel()  , member.getId());
                    redisCommands.sadd(CONNECTED_MEMBERS_KEY_PREFIX+channel.getIdChannel()  , member.getId());
                }

        }
        else throw new  AlreadyExistsException("Channel already exists  !"); 
    }


    public Optional<ChannelChat> findChannel(String id) throws Exception{
        String channelJson = redisCommands.hget(CHANNELS_KEY_PREFIX+id,"data").get();
        if(channelJson!=null){
            ChannelChat.Builder chb = ChannelChat.newBuilder();
            JSONConfig.fromJson(channelJson,chb);
            ChannelChat channel = chb.build();
            return Optional.of(channel);
        }
        return Optional.empty();
    }
    public void removeChannel(String channelid) throws Exception{
        if(redisCommands.exists(channelid).get()==0)
            throw new NonExistantException("Channel non existant to be removed ");
        redisCommands.del(CHANNELS_KEY_PREFIX+channelid);
        redisCommands.del(MEMBERS_KEY_PREFIX+channelid);
        redisCommands.del(CONNECTED_MEMBERS_KEY_PREFIX+channelid);
        redisCommands.del(MESSAGES_KEY_PREFIX+channelid);
        
    }
    public void connectToChannel(User user,ChannelChat channel ) throws Exception{
        String channelId = channel.getIdChannel();

        if(!redisCommands.sismember(CHANNELS_KEYS,channelId).get()){
            throw new NonExistantException("channel does not exist");
        }
        if(!redisCommands.sismember(MEMBERS_KEY_PREFIX+channelId,user.getId()).get()){
            throw new NoRightGivenException("user does not belong to this channel !");
        }
        redisCommands.sadd(CONNECTED_MEMBERS_KEY_PREFIX+channelId,user.getId());        
        
    }


    public void disconnectToChannel(User user,ChannelChat channel ) throws Exception{
        String channelId = channel.getIdChannel();

        if(!redisCommands.sismember(CHANNELS_KEYS,channelId).get()){
            throw new NonExistantException("channel does not exist");
        }
        if(!redisCommands.sismember(MEMBERS_KEY_PREFIX+channelId,user.getId()).get()){
            throw new NoRightGivenException("user does not belong to this channel !");
        }
        redisCommands.srem(CONNECTED_MEMBERS_KEY_PREFIX+channelId, user.getId());
    }

    public boolean checkIsAuthorizedToSend(User sender, ChannelChat channel) throws InterruptedException, ExecutionException {
        if((sender !=null) && ( channel !=null)){
            return redisCommands.sismember(CONNECTED_MEMBERS_KEY_PREFIX+channel.getIdChannel(),sender.getId()).get();
        }
        return false;
    }

    public boolean checkIsAuthorizedToReceive(User receiver, ChannelChat channel) {
        try {
            return checkIsAuthorizedToSend(receiver,channel);
        }    
        catch (Exception e) {
            return false;
        }
    }

    public void addMessage(ChannelChat channel , ChatMessage message){
        //TODO:add messaging 
    }

    public void getMessageList(String channelId) throws NonExistantException{
        // TODO: add all messages retrieve logic
    }

}
