package com.grpc.Service.Config;

import java.util.concurrent.atomic.AtomicBoolean;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisException;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

public class RedisConnection {
    RedisClient redisClient;
    StatefulRedisConnection<String, String> connection;
    AtomicBoolean connectionStatus = new AtomicBoolean(false);
    public RedisAsyncCommands<String, String> getConnectionCommands (){
        if(connectionStatus.get()){
            return  connection.async();
        }
        throw new RedisException("No connection found");
    }
    
    public synchronized void  stopConnection(){

        if(connectionStatus.get()){
            connection.close();
            redisClient.shutdown();
            connectionStatus.set(false);    
        }
        throw new RedisException("No connection found");
    }
    
    protected RedisConnection() {
            redisClient = RedisClient.create("redis://localhost:6379");
            try{
                connection  = redisClient.connect();
                connectionStatus.set(true);
                }
            catch(Exception e){
                System.out.println("Error connecting to Redis Server");
            }

    }
    public RedisClient getClient(){
        return redisClient;
    }
}

