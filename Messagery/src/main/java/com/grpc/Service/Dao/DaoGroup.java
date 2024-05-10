package com.grpc.Service.Dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

import com.grpc.Exception.AlreadyExistsException;
import com.grpc.Exception.NonExistantException;
import com.grpc.Service.ChatStream;
import com.grpc.Service.Config.RedisConnectionHolder;
import com.grpc.Service.Config.JSONConfig;
import com.grpc.proto.Messaging.ChannelChat;
import com.grpc.proto.Messaging.Filter;
import com.grpc.proto.Messaging.Group;
import com.grpc.proto.Messaging.User;

import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import reactor.core.publisher.Sinks.EmissionException;


public class DaoGroup implements DAO<Group,Filter>{

    private  RedisAsyncCommands<String, String>  RedisCommands = RedisConnectionHolder.Instance.getConnectionCommands();
    private final static String KEY_GROUP = "group:";
    private final static String KEY_GROUPS = "GROUPS";
    private final static String KEY_USER = "users:";
    private final static String KEY_ADMIN = "admins:";
    
    @Override
    public Optional<Group> find(String id)  throws InterruptedException, ExecutionException {
        if(RedisCommands.exists(KEY_GROUP+id).get()==0) 
            return Optional.empty();
        String jsonResult = RedisCommands.hget(KEY_GROUP+id,"data").get();
        Group.Builder grpb = Group.newBuilder();
        JSONConfig.fromJson(jsonResult,grpb);
        Group group = grpb.build();
        return Optional.of(group);
    }

        @Override
    public void getAll(ChatStream<Group> stream) throws InterruptedException, ExecutionException {
        RedisCommands.keys(KEY_GROUP+"*").get().stream()
                                              .forEach(key -> {
                                                String res = null;
                                                try {
                                                    res = RedisCommands.hget(key,"data").get();
                                                } catch (Exception e) {
                                                    e.printStackTrace();}
                                                 Group.Builder grpb = Group.newBuilder();
                                                JSONConfig.fromJson(res,grpb);
                                                Group group = grpb.build();
                                                stream.send(group);});

    }

    @Override
    public void save(Group t) throws Exception {
        if(RedisCommands.sismember(KEY_GROUPS, t.getId()).get()) throw new AlreadyExistsException("key already existing");
        else {
            RedisCommands.sadd(KEY_GROUPS, t.getId()).get();
            RedisCommands.hset(KEY_GROUP+t.getId(),"data",JSONConfig.toJson(t));
        }
    }

    public void save(Group t, List<User> _Users , List<User> _Admins) throws Exception {
        save(t);
        for(User user : _Users){
            //FIXME: adding users
            try{
                addUser(user, t.getId());
            }
            catch(Exception e ){
                System.out.println("Error : "+e.getMessage());
            }
        }

        for(User admin : _Admins){ 
            //FIXME: adding admins
            try{
                addAdmin(admin, t.getId());
            }
            catch(Exception e){
                System.out.println("Error : "+e.getMessage());
            }
        }
    }


    @Override
    public void update(Group t,String ... params) throws Exception {

            if(!RedisCommands.sismember(KEY_GROUPS, t.getId()).get()) 
                throw new AlreadyExistsException("key NOT existing");
            else{
                if(params.length==1){
                    String groupJson = RedisCommands.hget(KEY_GROUP+t.getId(),"data").get();
                    Group.Builder grpb = Group.newBuilder();
                    JSONConfig.fromJson(groupJson,grpb);
                    Group group = grpb.build();
                    Group updatedGroup = group.toBuilder().setName(params[0]).build();
                    RedisCommands.hset(KEY_GROUP+t.getId(),"data",JSONConfig.toJson(updatedGroup));
                }   
                else{
                    return;
                }
            }
        }   

    @Override
    public void delete(Group t) throws Exception{
        if(!RedisCommands.sismember(KEY_GROUPS, t.getId()).get()) 
            throw new NonExistantException("Error , could not find the GROUP to remove");
        else{
            RedisCommands.srem(KEY_GROUPS, t.getId()).get();
            RedisCommands.del(KEY_GROUP+t.getId()).get();
        }
    }

    

    // only the admin can do all these tasks

    public void addUser(User user , String group_id )throws Exception
    {
        if(!RedisCommands.sismember(KEY_GROUPS, group_id).get()) 
            throw new NonExistantException(group_id + " does not exist in the groups list");
        if(RedisCommands.sismember(KEY_USER+group_id, user.getId()).get()) 
            throw new AlreadyExistsException("User "+user.getId()+" already added to the group ");
        RedisCommands.sadd(KEY_USER+group_id,user.getId()).get();
    }

    public void addAdmin(User user , String group_id )throws Exception
    {
        if(!RedisCommands.sismember(KEY_GROUPS, group_id).get()){
            throw new NonExistantException(group_id + " does not exist in the groups list");
        }
        if(!RedisCommands.sismember(KEY_USER+group_id, user.getId()).get()){
            throw new NonExistantException("User "+user.getId()+" is not a member of the group ");//each admin must be a user at the start
        }
        if(RedisCommands.sismember(KEY_ADMIN+group_id, user.getId()).get()){
            throw new AlreadyExistsException(user.getId()+" is already an admin of the group "+group_id);
        }
            RedisCommands.sadd(KEY_ADMIN+group_id, user.getId()).get();
    }

    public void blockUser(User user , String group_id) throws Exception
    {

    }
    public void unblockUser(User user , String group_id) throws Exception{

    }

    public void removeUser(User user , String group_id) throws Exception {

    }


    @Override
    public void find(Filter filter, ChatStream<Group> stream) throws InterruptedException, ExecutionException{
        RedisCommands.keys(KEY_GROUP+"*").get().stream()
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
            Group.Builder grpb = Group.newBuilder();
            String str = RedisCommands.hget(key,"data").get();
            JSONConfig.fromJson(str,grpb);
            Group group = grpb.build();
              stream.send(group);
          } catch (Exception e) {
              e.printStackTrace();
          }
        });
    }

}


//FIXME: kammaltha lkol