package com.grpc.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import com.grpc.Exception.AlreadyExistsException;
import com.grpc.Exception.NoRightGivenException;
import com.grpc.Exception.NonExistantException;
import com.grpc.protoCompiled.Messaging.ChannelChat;
import com.grpc.protoCompiled.Messaging.Channel_STATUS;
import com.grpc.protoCompiled.Messaging.Channel_type;
import com.grpc.protoCompiled.Messaging.ChatMessage;
import com.grpc.protoCompiled.Messaging.ConnectChannelRequest;
import com.grpc.protoCompiled.Messaging.DisconnectChannelRequest;
import com.grpc.protoCompiled.Messaging.JoinResponse;
import com.grpc.protoCompiled.Messaging.ReceiveMessageRequest;
import com.grpc.protoCompiled.Messaging.SearchGroupRequest;
import com.grpc.protoCompiled.Messaging.SearchUserRequest;
import com.grpc.protoCompiled.Messaging.SendMessageRequest;
import com.grpc.protoCompiled.Messaging.SetStatusRequest;
import com.grpc.protoCompiled.Messaging.StatusResponse;
import com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest;
import com.grpc.protoCompiled.Messaging.User;
import com.grpc.protoCompiled.Messaging.Empty;
import com.grpc.protoCompiled.Messaging.Filter;
import com.grpc.protoCompiled.Messaging.FindGroupRequest;
import com.grpc.protoCompiled.Messaging.FindUserRequest;
import com.grpc.protoCompiled.Messaging.Group;
import com.grpc.protoCompiled.Messaging.JoinChannelRequest;
import com.grpc.Service.Dao.DaoGroup;
import com.grpc.Service.Dao.DaoUser;
import com.grpc.Service.proto.ChatServiceGrpc.ChatServiceImplBase;

import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class chatservice extends ChatServiceImplBase {
    private static final Logger logger = Logger.getLogger(chatservice.class.getName());
    private DaoUser daoUser;
    private DaoGroup daoGroup;
    private MessageService messageService;
    private channelservice channelservice;
    public chatservice(){
        daoUser = new DaoUser();
        daoGroup = new DaoGroup();
        messageService = new MessageService();
        channelservice = new channelservice();
    }


    @Override
    public void join (User user, StreamObserver<JoinResponse> responseObserver){
        UUID uuid;
        System.out.println(user);
        if(user.getId()==null || user.getId()==""){
            uuid = UUID.randomUUID();
        }
        else{
            try{
                uuid = UUID.fromString(user.getId());
            }catch(IllegalArgumentException e){
                responseObserver.onError(
                    Status.ALREADY_EXISTS
                        .withDescription(e.getMessage())
                        .asRuntimeException()
                );
                return;
            }
        } 
        if(Context.current().isCancelled()) {
            logger.info("request is cancelled  ");
            responseObserver.onError(
                Status.CANCELLED
                .withDescription("Request aborted")
                .asRuntimeException()
            );
            return;
        }
        User other = user.toBuilder().setId(uuid.toString()).build();
        try{
            daoUser.save(other);
            logger.info("user "+other.getId()+" successfully added to users list ! ");
        }
        catch (AlreadyExistsException e ){
            logger.info(e.getMessage());
            responseObserver.onError(
                Status.ALREADY_EXISTS
                .withDescription(e.getMessage())
                .asRuntimeException());
                return;
        }
        catch (Exception e ){
            logger.info("Internal Error ocurred");
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return ;

        }
        JoinResponse resp = JoinResponse.newBuilder().setId(other.getId()).build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
        logger.info("Adding completed ..");      
    }

    @Override 
    public void joinChannel(JoinChannelRequest request , StreamObserver<ChannelChat> responseObserver){
        ChannelChat channel;
        User user1 = request.getUser1();
        User user2 = request.getUser2();
        try{
            if(!daoUser.find(user1.getId()).isPresent() || !daoUser.find(user2.getId()).isPresent()){
                responseObserver.onError(
                    Status.UNKNOWN
                    .withDescription("User Unkown !")
                    .asRuntimeException()
                );
                System.out.println("moush maoujoud");
                return;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
                );
                return;
            }     
        String Channel_id = generateID(user1,user2);
        if(Context.current().isCancelled()) {
            logger.info("request is cancelled  ");
            responseObserver.onError(
                Status.CANCELLED
                .withDescription("Request aborted")
                .asRuntimeException()
            );
            return;
        }
        try{
            
            Optional<ChannelChat> option = channelservice.findChannel(Channel_id);
            if(option.isPresent())
            {
                channel = option.get();
                channelservice.connectToChannel(user1, channel);
            }
            else {
                ArrayList<User> users = new ArrayList<User>();
                users.add(user1);
                users.add(user2);
                channel = channelservice.createChannel(Channel_id, Channel_STATUS.PRIVATE, Channel_type.USER);
                System.out.println("snaat channel "+channel.getIdChannel());
                channelservice.addChannel(channel , users );
            }
        }
        catch(Exception e){
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
                
            );
            return;
        }
        responseObserver.onNext(channel);
        responseObserver.onCompleted();
        logger.info("Joined the private channel "+channel.getIdChannel()+" with user "+user2.getName());
    
    }

    @Override
    public void connectToChannel(ConnectChannelRequest request , StreamObserver<Empty> responseObserver){
        User user = request.getUser();
        ChannelChat channel = request.getChannel();
        if(Context.current().isCancelled()) {
            logger.info("request is cancelled  ");
            responseObserver.onError(
                Status.CANCELLED
                .withDescription("Request aborted")
                .asRuntimeException()
            );
            return;
        }
        try{
            this.channelservice.connectToChannel(user, channel);
        }
        catch(NonExistantException e){
            responseObserver.onError(
                Status.NOT_FOUND
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        } catch (NoRightGivenException e) {
            responseObserver.onError(
                Status.PERMISSION_DENIED
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        }
        catch (Exception e){
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        }

        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
        logger.info("User "+user.getName()+" connected successfully to channel "+channel.getIdChannel());

    }

    @Override
    public void disconnectToChannel(DisconnectChannelRequest request , StreamObserver<Empty> responseObserver){
        User user = request.getUser();
        ChannelChat channel = request.getChannel();
        if(Context.current().isCancelled()) {
            logger.info("request is cancelled  ");
            responseObserver.onError(
                Status.CANCELLED
                .withDescription("Request aborted")
                .asRuntimeException()
            );
            return;
        }
        try{
            this.channelservice.disconnectToChannel(user, channel);
        }
        catch(NonExistantException e){
            responseObserver.onError(
                Status.NOT_FOUND
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        } catch (NoRightGivenException e) {
            responseObserver.onError(
                Status.PERMISSION_DENIED
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        }
        catch (Exception e){
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        }
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
        logger.info("User "+user.getName()+" disconnected successfully from channel "+channel.getIdChannel());
    }

    @Override
    public void sendMsg(SendMessageRequest request,StreamObserver<Empty> responseObserver) {
         ChannelChat channel = request.getChannel();
         ChatMessage content = request.getMessage();
         User sender = request.getSender();
         if(channel==null || content==null ||sender==null){
            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                    .withDescription("Invalid request: Channel, message content, and sender must be provided.")
                    .asRuntimeException()
            );
            return;
         }
         try{

             if(!channelservice.checkIsAuthorizedToSend(sender , channel )){
                 responseObserver.onError(
                     Status.PERMISSION_DENIED
                     .withDescription("Sender is not authorized to send messages to the specified channel. ")
                     .asRuntimeException());
                     return;
                }
            }
            catch(Exception e){
                responseObserver.onError(
                    Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException()
                );
                return;
            }
                    
         if(Context.current().isCancelled()){
            logger.info("request is cancelled  ");
            responseObserver.onError(
                Status.CANCELLED
                .withDescription("Request aborted")
                .asRuntimeException()
            );
            return;
         }
         try{
            logger.info("Sending  message ["+content.getMsg()+"]\n Channel "+channel.getIdChannel()+"\n Sender "+sender.getName()+"\n");
            messageService.sendMessage(sender,channel,content);
         }
         catch(Exception e ){
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
                );
            return;    
            
         }
        

    }


    @Override
    public void receiveMsg(ReceiveMessageRequest request,StreamObserver<ChatMessage> responseObserver) {
        User receiver = request.getReceiver();
        ChannelChat channel = request.getChannel();
        Thread thread = new Thread(()  -> {           
            if(Context.current().isCancelled()) {
                logger.info("request is cancelled  ");
                responseObserver.onError(
                    Status.CANCELLED
                    .withDescription("Request aborted")
                    .asRuntimeException()
                );
                return;
            }

            if(!channelservice.checkIsAuthorizedToReceive(receiver , channel )){
                responseObserver.onError(
                    Status.PERMISSION_DENIED
                    .withDescription("Receiver is not authorized to receive messages from the specified channel. ")
                    .asRuntimeException());
                return;
             }

            try{
                this.messageService.ReceiveMessage(receiver, channel, new ChatStream<ChatMessage>() {
                    @Override
                    public void send(ChatMessage message){
                        logger.info("👎🏽message received  :"+message.getMsg());
                        responseObserver.onNext(message);
                    }
                }
                );
            }
            catch(NonExistantException e){
                e.printStackTrace();
                responseObserver.onError(
                    Status.NOT_FOUND
                    .withDescription(e.getMessage())
                    .asRuntimeException()
                );
                return;
            }
            catch(Exception e ){
                e.printStackTrace();
                responseObserver.onError(
                    Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException()
                );
                return;
            }
            }
        );
        thread.start();
    }


    @Override
    public void getAllUsers(Empty request,StreamObserver<User>responseObserver) {
        logger.info("Showing all users !");
        try{
        daoUser.getAll(new ChatStream<User>() {
            @Override
            public void send (User user){
                responseObserver.onNext(user);
            }
        });}
        catch(Exception e){
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        }
        responseObserver.onCompleted();
        logger.info("Search completed ..");      
    }
    
    @Override
    public void getAllGroups(Empty request,StreamObserver<Group>responseObserver) {
        logger.info("Showing all groups !");

       try{
            daoGroup.getAll(new ChatStream<Group>() {
            @Override
            public synchronized void send (Group group){
                logger.info("Group  "+group);
                responseObserver.onNext(group);
            }
        });}
        catch(Exception e){
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        }
        responseObserver.onCompleted();
        logger.info("Search completed ..");      
    }



    @Override
    public void getChannel(ChannelChat request,StreamObserver<User> responseObserver) {

    }


    @Override
    public void searchUser(SearchUserRequest request ,StreamObserver<User> responseObserver){
    Filter filter = request.getName();
    logger.info("Got a search request of users with filter !\n"+filter);
    try{
    daoUser.find(filter,new ChatStream<User>() {
        @Override
        public void send (User user){
            logger.info("User  "+user);
            responseObserver.onNext(user);
        }
    });}
    catch(Exception e){
        responseObserver.onError(
            Status.INTERNAL
            .withDescription(e.getMessage())
            .asRuntimeException()
        );
        return;
    }
    responseObserver.onCompleted();
    logger.info("search completed ..");     
}

    @Override
    public void searchGroup(SearchGroupRequest request ,StreamObserver<Group> responseObserver){
        Filter filter = request.getName();
        logger.info("Got a search request of groups with filter !\n"+filter);
        try{

        daoGroup.find(filter,new ChatStream<Group>() {
            @Override
            public void send (Group group){
                logger.info("Group  "+group);
                responseObserver.onNext(group);
            }
        });}
        catch(Exception e){
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        }
        responseObserver.onCompleted();
        logger.info("search completed ..");
    }


    @Override 
    public void stopReceiveMsg(StopReceiveMessageRequest request, StreamObserver<Empty> responseObserver){
        User user = request.getReceiver();
        ChannelChat channel = request.getChannel();
        if(user==null || channel==null){
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                .withDescription("Please provide a valide user and Channel")
                .asRuntimeException()
            );
            return;
        }
        messageService.StopReceiveMessage(user,channel);
        logger.info("User "+user.getName()+"stopped receiving messages from the channel ");
        responseObserver.onCompleted();

    }

    private String generateID(User user1, User user2){
        String id1 =user1.getId();
        String id2 =user2.getId();
        String[] sortedId ={id1,id2} ;
        Arrays.sort(sortedId);       
        return "private:"+sortedId[0]+":"+sortedId[1];
    }

    @Override
    public void getConnectedChannel(User request , StreamObserver<ChannelChat> responseObserver){
        User user = request;
        try{
        this.channelservice.getConnectedChannel(user, new ChatStream<ChannelChat>() {
            @Override
            public void send (ChannelChat channel){
                logger.info("Channel  "+channel);
                responseObserver.onNext(channel);
            }
        });}
        catch(Exception e){
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return;
        }
        responseObserver.onCompleted();
        logger.info("search completed ..");
    }


    @Override
    public void setStatus(SetStatusRequest request, StreamObserver<Empty> responseObserver){
        User user = request.getUser() ;
        com.grpc.protoCompiled.Messaging.Status status = request.getStatus();
        if(user==null || status==null){
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                .withDescription("Please provide a valide user and Status")
                .asRuntimeException()
            );
            return ;
            
        }
        try{
            daoUser.setStatus(user,status);
        }
        finally {
            logger.info("User "+user.getName()+" set to "+status);
        }
    }

    @Override
    public void getStatus (User request, StreamObserver<StatusResponse> responseObserver){
        User user =request ;
        if(user==null){
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                .withDescription("Please provide a valid user")
                .asRuntimeException()
            );
            return ;
        }
        com.grpc.protoCompiled.Messaging.Status status = null;
        try{
            status = daoUser.getStatus(user);
            StatusResponse statusResponse = StatusResponse.newBuilder().setStatus(status).build();
            responseObserver.onNext(statusResponse);
        }
        finally{
            logger.info("User : "+user.getName()+" status : "+status);
        }

    }

    @Override 
    public void findUser(FindUserRequest request, StreamObserver<User> responseObserver){
        String id = request.getId();
        if(id==null){
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                .withDescription("Please provide a valid user")
                .asRuntimeException()
            );
            return ;    
        }
        try{
            Optional<User> search = daoUser.find(id);
            if(search.isEmpty()){
                responseObserver.onError(
                    Status.NOT_FOUND
                    .withDescription("User not found")
                    .asRuntimeException()
                );
                return;
            };
            User user = search.get();
            logger.info("User found : "+user);
            responseObserver.onNext(user);
            responseObserver.onCompleted();
        }catch(Exception e){ 
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return; 
        }
    }



    @Override 
    public void findGroup(FindGroupRequest request, StreamObserver<Group> responseObserver){
        String id = request.getId();
        if(id==null){
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                .withDescription("Please provide a valid group")
                .asRuntimeException()
            );
            return ;    
        }
        try{
            Optional<Group> search = daoGroup.find(id);
            if(search.isEmpty()){
                responseObserver.onError(
                    Status.NOT_FOUND
                    .withDescription("Group not found")
                    .asRuntimeException()
                );
                return;
            };
            Group group = search.get();
            logger.info("Group found : "+group);
            responseObserver.onNext(group);
            responseObserver.onCompleted();
        }catch(Exception e){ 
            responseObserver.onError(
                Status.INTERNAL
                .withDescription(e.getMessage())
                .asRuntimeException()
            );
            return; 
        }
    }

}
