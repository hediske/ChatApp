package com.grpc.Service.Client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.grpc.Service.MessageService;
import com.grpc.Service.Config.JSONConfig;
import com.grpc.Service.generator.Generator;
import com.grpc.Service.proto.ChatServiceGrpc;
import com.grpc.Service.proto.ChatServiceGrpc.ChatServiceBlockingStub;
import com.grpc.protoCompiled.Messaging.ChannelChat;
import com.grpc.protoCompiled.Messaging.ChatMessage;
import com.grpc.protoCompiled.Messaging.ConnectChannelRequest;
import com.grpc.protoCompiled.Messaging.DisconnectChannelRequest;
import com.grpc.protoCompiled.Messaging.Empty;
import com.grpc.protoCompiled.Messaging.Filter;
import com.grpc.protoCompiled.Messaging.Group;
import com.grpc.protoCompiled.Messaging.JoinChannelRequest;
import com.grpc.protoCompiled.Messaging.JoinResponse;
import com.grpc.protoCompiled.Messaging.ReceiveMessageRequest;
import com.grpc.protoCompiled.Messaging.SearchGroupRequest;
import com.grpc.protoCompiled.Messaging.SearchUserRequest;
import com.grpc.protoCompiled.Messaging.SendMessageRequest;
import com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest;
import com.grpc.protoCompiled.Messaging.User;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.Status.Code;
import io.grpc.StatusRuntimeException;

public class ChatClientJava {
    private static final Logger logger = Logger.getLogger(ChatClientJava.class.getName()) ;

    private ManagedChannel channel ; 
    private ChatServiceBlockingStub stub;
    public ChatClientJava(String host , int port){
       
        this.channel=ManagedChannelBuilder.forAddress(host , port)
                        .usePlaintext()
                        .keepAliveTime(150, TimeUnit.SECONDS)
                        .build();
        this.stub = ChatServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException{
        channel.awaitTermination(
            5,TimeUnit.SECONDS);
    }

    public void join(User user){
        JoinResponse joinResponse = JoinResponse.getDefaultInstance();
        try{
            joinResponse = stub.withDeadlineAfter(10,TimeUnit.SECONDS).join(user);
        }
        catch(StatusRuntimeException e){
            if(e.getStatus().getCode() == Status.Code.ALREADY_EXISTS){
                logger.info("Error adding the user . A useer with the same Id already exists  !");
                return;
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE,"request failed ! ",e.getMessage());
            return;
        }
        logger.info("User added successfully");


    }

    public ChannelChat joinChannel(User user1,User user2){
        JoinChannelRequest joinchannelrequest = JoinChannelRequest.newBuilder().setUser1(user1).setUser2(user2).build();
        ChannelChat response = ChannelChat.getDefaultInstance();
        try{
            response = stub.withDeadlineAfter(10,TimeUnit.SECONDS).joinChannel(joinchannelrequest);
        }
        catch(StatusRuntimeException e){
            if(e.getStatus().getCode()==Status.Code.UNKNOWN ){
                logger.info("Sorry there is an error in the Users provided ! Unkown User");
                return null;
            }
        }
        catch(Exception e ){
            logger.log(Level.SEVERE, "request failed ! ",e.getMessage());
            return null;
        }
            logger.info("User successfully joined the Channel ");
            return response;
        
    } 


    public void getUsers(){
        logger.info("Searching for all Users ");
        Empty empty = Empty.getDefaultInstance();
        Iterator<User> response=null;
        try{
             response = stub.withDeadlineAfter(10,TimeUnit.SECONDS).getAllUsers(empty);
             if(response==null)
                {
                    logger.info("No User found ");
                    return;
                }
            while(response.hasNext()){
                User user = response.next();
                logger.info("User  :   "+user.getId() +"  "+user.getName());
            }
        }finally{
            logger.info("All users are found  ! ");
        }
    }

    public void getGroups(){
        logger.info("Searching for all Groups ");
        Empty empty = Empty.getDefaultInstance();
        Iterator<Group> response=null;
        try{
             response = stub.withDeadlineAfter(10,TimeUnit.SECONDS).getAllGroups(empty);
             if(response==null)
                {
                    logger.info("No Group found ");
                    return;
                }
            while(response.hasNext()){
                Group group = response.next();
                logger.info("Group  :   "+group);
            }
        }finally{
            logger.info("All groups are found  ! ");
        }
    }

    public void connectToChannel(User user , ChannelChat channel){
        ConnectChannelRequest request = ConnectChannelRequest.newBuilder()
                                                            .setChannel(channel)
                                                            .setUser(user)
                                                            .build();
        Empty response = Empty.getDefaultInstance();
        try{
            response = stub.withDeadlineAfter(10, TimeUnit.SECONDS).connectToChannel(request);
            if(response==null){
                logger.info("No response received");
                
            }


        }
        catch(StatusRuntimeException e){
            Code code = e.getStatus().getCode();
            if(code==Status.Code.PERMISSION_DENIED){
                logger.info("User does not have the permission to access to this channel");
                return;
            }
            if(code==Status.Code.NOT_FOUND){
                logger.info("Please provide a valid User and a valid Channel");
                return;
            }

        }
        catch(Exception e){
            logger.log(Level.SEVERE,"Error in the request ", e.getMessage());
            return;
        }

        logger.info("successfully connected to the channel ");
    }

    public void disconnectFromChannel(User user , ChannelChat channel){
        DisconnectChannelRequest request = DisconnectChannelRequest.newBuilder()
                                                            .setChannel(channel)
                                                            .setUser(user)
                                                            .build();
        Empty response = Empty.getDefaultInstance();
        try{
            response = stub.withDeadlineAfter(10, TimeUnit.SECONDS).disconnectToChannel(request);
            if(response==null){
                logger.info("No response received");
                
            }

        }
        catch(StatusRuntimeException e){
            Code code = e.getStatus().getCode();
            if(code==Status.Code.PERMISSION_DENIED){
                logger.info("User does not have the permission to access to this channel");
                return;
            }
            if(code==Status.Code.NOT_FOUND){
                logger.info("Please provide a valid User and a valid Channel");
                return;
            }

        }
        catch(Exception e){
            logger.log(Level.SEVERE,"Error in the request ", e.getMessage());
            return;
        }

        logger.info("successfully disconnected from the channel ");
    }

    public void findUser( Filter filter){
        Iterator<User> response=null;
        SearchUserRequest request = SearchUserRequest.newBuilder().setName(filter).build();
        try{
             response = stub.withDeadlineAfter(10,TimeUnit.SECONDS).searchUser(request);
             if(response==null)
                {
                    logger.info("No User found ");
                    return;
                }
            while(response.hasNext()){
                User user = response.next();
                logger.info("User  :   "+user.getId() +"  "+user.getName());
            }
        }finally{
            logger.info("Search for Users with Name "+filter.getName()+" ! ");
        }
    }

    public void findGroup(Filter filter){
        SearchGroupRequest request = SearchGroupRequest.newBuilder().setName(filter).build();
        Iterator<Group> response=null;
        try{
             response = stub.withDeadlineAfter(10,TimeUnit.SECONDS).searchGroup(request);
             if(response==null)
                {
                    logger.info("No Group found ");
                    return;
                }
            while(response.hasNext()){
                Group group = response.next();
                logger.info("Group  :   "+group);
            }
        }
        finally{
            logger.info("Search for Groups with Name "+filter.getName()+" ! ");
        }
    }


    public void sendMessage(User sender , ChannelChat channel ,String content){
        
        ChatMessage message = MessageService.createMessage(sender, content);
                                    
        SendMessageRequest request = SendMessageRequest.newBuilder()
                                    .setSender(sender)
                                    .setChannel(channel)
                                    .setMessage(message)
                                    .build();
        Empty response = Empty.getDefaultInstance();
        
        try{

            response = stub.withDeadlineAfter(500, TimeUnit.MILLISECONDS).sendMsg(request);
        }
        catch(StatusRuntimeException e){
            Code code = e.getStatus().getCode();
            if(code==Status.Code.PERMISSION_DENIED){
                logger.info("User does not have the permission to send messages in this channel");
                return;
            }
            if(code==Status.Code.INVALID_ARGUMENT){
                logger.info("Channel, message content, and sender must be provided.");
                return;
            }

        }
        catch(Exception e)  {
            logger.log(Level.SEVERE, "request failed ! ",e.getMessage());
            return;
        }

        logger.info("User sent a new message with content  ");
    }

    public void receiveMsg(User receiver , ChannelChat channel){
        Iterator<ChatMessage> result = null ;
        ReceiveMessageRequest request = ReceiveMessageRequest.newBuilder()
                                            .setChannel(channel)
                                            .setReceiver(receiver)
                                            .build();
        try {
            AtomicReference<ChatServiceBlockingStub > stubAtomic = new AtomicReference<>(stub);
            logger.info("Started receiving from the channel ");
            Thread thread = new Thread( 
                () ->{
                while(true){
                    Iterator<ChatMessage> ListMessages = stubAtomic.get().receiveMsg(request);
                    while (ListMessages.hasNext()) {
                        ChatMessage message = ListMessages.next();
                        logger.info("Received message: (;" + message.getMsg());
                    }
                    
                }
            }
            );
            thread.start();

        } catch (StatusRuntimeException e) {
            Code code = e.getStatus().getCode();
            if (code == Status.Code.PERMISSION_DENIED) {
                logger.info("User does not have permission to receive messages from this channel.");
                return;
            }
            if(code == Status.Code.NOT_FOUND){
                logger.info("Channel not existing  ! ");
                return;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in receiving messages: " + e.getMessage());
            return;
        }
    }

    public void stopReceiveMsg(User user , ChannelChat channel){

        StopReceiveMessageRequest stopReceiveMessageRequest = StopReceiveMessageRequest.newBuilder()
                                                                .setChannel(channel)
                                                                .setReceiver(user)
                                                                .build();
        Empty empty = Empty.getDefaultInstance();
        try{
            empty = stub.withDeadlineAfter(1, TimeUnit.SECONDS).stopReceiveMsg(stopReceiveMessageRequest);
            
        }catch(StatusRuntimeException e){
            Object code = e.getStatus().getCode();
            if(code == Status.Code.INVALID_ARGUMENT){
                logger.info("Please provide a valid user and channel ");
                return;
            }

        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Error in receiving messages: " + e.getMessage());
            return;
        }
        
        logger.info("Stopped receiving messages from the channel successfully !");

    }

    public static void main(String[] args) throws InterruptedException{
        ChatClientJava javaClient = new ChatClientJava("0.0.0.0", 8080); //connect to localhost
        Generator generator = new Generator();
        try{
            User user1 = generator.newUser();
            User user2 = generator.newUser();
            javaClient.join(user1);
            javaClient.join(user2);
            ChannelChat channel = javaClient.joinChannel(user2, user1);
            javaClient.receiveMsg(user2, channel);
            // Thread.sleep(200);
            // javaClient.sendMessage(user1, channel, "1");
            // javaClient.sendMessage(user1, channel, "2");
            // javaClient.sendMessage(user1, channel, "3");
            // javaClient.sendMessage(user1, channel, "4");
            // javaClient.sendMessage(user1, channel, "5");


            // Thread.sleep(1000);
            // javaClient.stopReceiveMsg(user2, channel);
            // javaClient.stopReceiveMsg(user1, channel);
            // javaClient.getUsers();
            // javaClient.findUser(Filter.newBuilder().setName("Wsuq").build());

        }
        catch(Exception e){
            logger.log(Level.SEVERE,"Error in the request ", e.getMessage());
        }
        finally{
            javaClient.shutdown();
        }
    }


    private static String dateFormat(long seconds , int nanos){
        LocalDateTime date  = LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds,nanos), ZoneOffset.ofHours(1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        return date.format(formatter);
    }

}
