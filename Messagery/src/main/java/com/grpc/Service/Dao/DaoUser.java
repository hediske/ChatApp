package com.grpc.Service.Dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

import com.grpc.Exception.AlreadyExistsException;
import com.grpc.Exception.NonExistantException;
import com.grpc.Service.ChatStream;
import com.grpc.Service.Config.RedisConnection;
import com.grpc.Service.Config.RedisConnectionHolder;
import com.grpc.Service.Config.JSONConfig;
import com.grpc.protoCompiled.Messaging.Filter;
import com.grpc.protoCompiled.Messaging.Group;
import com.grpc.protoCompiled.Messaging.Status;
import com.grpc.protoCompiled.Messaging.Group.Builder;
import com.grpc.protoCompiled.Messaging.User;

import io.lettuce.core.api.async.RedisAsyncCommands;

public class DaoUser implements DAO<User,Filter> {

    private  RedisAsyncCommands<String, String>  RedisCommands = RedisConnectionHolder.Instance.getConnectionCommands();
    private final static String KEY_USER = "user:";
    private final static String KEY_USERS = "USERS";
    @Override
    public Optional<User> find(String id) throws InterruptedException, ExecutionException {
        if(!RedisCommands.sismember(KEY_USERS,id).get()) 
            return Optional.empty();
        String jsonResult = RedisCommands.hget(KEY_USER+id,"data").get();
        User.Builder userb = User.newBuilder();
        JSONConfig.fromJson(jsonResult,userb);
        User user = userb.build();
        return Optional.of(user);
    }
    

    @Override
    public void getAll(ChatStream<User> stream) throws InterruptedException, ExecutionException {
        RedisCommands.keys(KEY_USER+"*").get().stream()
                                              .forEach(key -> {
                                                String res = null;
                                                try {
                                                    res = RedisCommands.hget(key,"data").get();
                                                } catch (Exception e) {
                                                    e.printStackTrace();}
                                                User.Builder userb = User.newBuilder();
                                                JSONConfig.fromJson(res,userb);
                                                User user = userb.build();
                                                stream.send(user);});
    }

    @Override
    public void save(User t) throws Exception {
    if(RedisCommands.sismember(KEY_USERS, t.getId()).get()) throw new AlreadyExistsException("key already existing");
    else {
        RedisCommands.sadd(KEY_USERS, t.getId()).get();
        RedisCommands.hset(KEY_USER+t.getId(),"data",JSONConfig.toJson(t));
    }
    }

    @Override
    public void update(User t,String ... params) throws Exception {
        if(!RedisCommands.sismember(KEY_USERS, t.getId()).get()) 
            throw new AlreadyExistsException("key NOT existing");
        else{
            if(params.length==1){
                String userJson = RedisCommands.hget(KEY_USER+t.getId(),"data").get();         
                User.Builder usrb = User.newBuilder();
                JSONConfig.fromJson(userJson,usrb);
                User user = usrb.build();
                User updatedUser = user.toBuilder().setName(params[0]).build();
                RedisCommands.hset(KEY_USER+t.getId(),"data",JSONConfig.toJson(updatedUser));
            }   
            else{
                return;
            }
        }
    }   

    @Override
    public void delete(User t) throws Exception{
        if(!RedisCommands.sismember(KEY_USERS, t.getId()).get()) 
            throw new NonExistantException("Error , could not find the user to remove");
        else{
            RedisCommands.srem(KEY_USERS, t.getId()).get();
            RedisCommands.del(KEY_USER+t.getId()).get();
        }
    }


    @Override
    public void find(Filter filter, ChatStream<User> stream) throws InterruptedException, ExecutionException {
        RedisCommands.keys(KEY_USER+"*").get().stream()
                                          .filter(key -> {
                                            try {
                                                return RedisCommands.hget(key,"data").get().contains(filter.getName());
                                            }
                                             catch (Exception e) {
                                                e.printStackTrace();
                                                return false;
                                            }
                                        })
                                          .parallel()
                                          .forEach(key -> {
                                            try {
                                                User.Builder userb = User.newBuilder();
                                                String str = RedisCommands.hget(key,"data").get();
                                                JSONConfig.fromJson(str,userb);
                                                stream.send(userb.build());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                          });
    }

    public void setStatus(User user , Status status ){
        try{
            String userJson = RedisCommands.hget(KEY_USER+user.getId(),"data").get();
            User.Builder builder = User.newBuilder();
            JSONConfig.fromJson(userJson,builder);
            User newUser = builder.setStatus(status).build();
            RedisCommands.hset(KEY_USER+user.getId(),"data",JSONConfig.toJson(newUser));
        }
        catch(Exception e ){
            e.printStackTrace();
        }
    }


    public Status getStatus(User user){
        try{
            String userJson = RedisCommands.hget(KEY_USER+user.getId(),"data").get();
            User.Builder builder = User.newBuilder();
            JSONConfig.fromJson(userJson,builder);
            User newUser = builder.build();
            return newUser.getStatus();}
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    
}
//FIXME: kammaltha lkol