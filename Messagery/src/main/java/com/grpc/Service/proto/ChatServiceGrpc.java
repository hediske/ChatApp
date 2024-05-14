package com.grpc.Service.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: chat-service.proto")
public final class ChatServiceGrpc {

  private ChatServiceGrpc() {}

  public static final String SERVICE_NAME = "chat.grpc.ChatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User,
      com.grpc.protoCompiled.Messaging.JoinResponse> getJoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "join",
      requestType = com.grpc.protoCompiled.Messaging.User.class,
      responseType = com.grpc.protoCompiled.Messaging.JoinResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User,
      com.grpc.protoCompiled.Messaging.JoinResponse> getJoinMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User, com.grpc.protoCompiled.Messaging.JoinResponse> getJoinMethod;
    if ((getJoinMethod = ChatServiceGrpc.getJoinMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getJoinMethod = ChatServiceGrpc.getJoinMethod) == null) {
          ChatServiceGrpc.getJoinMethod = getJoinMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.User, com.grpc.protoCompiled.Messaging.JoinResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "join"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.JoinResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("join"))
                  .build();
          }
        }
     }
     return getJoinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.JoinChannelRequest,
      com.grpc.protoCompiled.Messaging.ChannelChat> getJoinChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "joinChannel",
      requestType = com.grpc.protoCompiled.Messaging.JoinChannelRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.ChannelChat.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.JoinChannelRequest,
      com.grpc.protoCompiled.Messaging.ChannelChat> getJoinChannelMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.JoinChannelRequest, com.grpc.protoCompiled.Messaging.ChannelChat> getJoinChannelMethod;
    if ((getJoinChannelMethod = ChatServiceGrpc.getJoinChannelMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getJoinChannelMethod = ChatServiceGrpc.getJoinChannelMethod) == null) {
          ChatServiceGrpc.getJoinChannelMethod = getJoinChannelMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.JoinChannelRequest, com.grpc.protoCompiled.Messaging.ChannelChat>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "joinChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.JoinChannelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.ChannelChat.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("joinChannel"))
                  .build();
          }
        }
     }
     return getJoinChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ConnectChannelRequest,
      com.grpc.protoCompiled.Messaging.Empty> getConnectToChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "connectToChannel",
      requestType = com.grpc.protoCompiled.Messaging.ConnectChannelRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ConnectChannelRequest,
      com.grpc.protoCompiled.Messaging.Empty> getConnectToChannelMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ConnectChannelRequest, com.grpc.protoCompiled.Messaging.Empty> getConnectToChannelMethod;
    if ((getConnectToChannelMethod = ChatServiceGrpc.getConnectToChannelMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getConnectToChannelMethod = ChatServiceGrpc.getConnectToChannelMethod) == null) {
          ChatServiceGrpc.getConnectToChannelMethod = getConnectToChannelMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.ConnectChannelRequest, com.grpc.protoCompiled.Messaging.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "connectToChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.ConnectChannelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("connectToChannel"))
                  .build();
          }
        }
     }
     return getConnectToChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.DisconnectChannelRequest,
      com.grpc.protoCompiled.Messaging.Empty> getDisconnectToChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "disconnectToChannel",
      requestType = com.grpc.protoCompiled.Messaging.DisconnectChannelRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.DisconnectChannelRequest,
      com.grpc.protoCompiled.Messaging.Empty> getDisconnectToChannelMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.DisconnectChannelRequest, com.grpc.protoCompiled.Messaging.Empty> getDisconnectToChannelMethod;
    if ((getDisconnectToChannelMethod = ChatServiceGrpc.getDisconnectToChannelMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getDisconnectToChannelMethod = ChatServiceGrpc.getDisconnectToChannelMethod) == null) {
          ChatServiceGrpc.getDisconnectToChannelMethod = getDisconnectToChannelMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.DisconnectChannelRequest, com.grpc.protoCompiled.Messaging.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "disconnectToChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.DisconnectChannelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("disconnectToChannel"))
                  .build();
          }
        }
     }
     return getDisconnectToChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SendMessageRequest,
      com.grpc.protoCompiled.Messaging.Empty> getSendMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMsg",
      requestType = com.grpc.protoCompiled.Messaging.SendMessageRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SendMessageRequest,
      com.grpc.protoCompiled.Messaging.Empty> getSendMsgMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SendMessageRequest, com.grpc.protoCompiled.Messaging.Empty> getSendMsgMethod;
    if ((getSendMsgMethod = ChatServiceGrpc.getSendMsgMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSendMsgMethod = ChatServiceGrpc.getSendMsgMethod) == null) {
          ChatServiceGrpc.getSendMsgMethod = getSendMsgMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.SendMessageRequest, com.grpc.protoCompiled.Messaging.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "sendMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.SendMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("sendMsg"))
                  .build();
          }
        }
     }
     return getSendMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ReceiveMessageRequest,
      com.grpc.protoCompiled.Messaging.ChatMessage> getReceiveMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveMsg",
      requestType = com.grpc.protoCompiled.Messaging.ReceiveMessageRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.ChatMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ReceiveMessageRequest,
      com.grpc.protoCompiled.Messaging.ChatMessage> getReceiveMsgMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ReceiveMessageRequest, com.grpc.protoCompiled.Messaging.ChatMessage> getReceiveMsgMethod;
    if ((getReceiveMsgMethod = ChatServiceGrpc.getReceiveMsgMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getReceiveMsgMethod = ChatServiceGrpc.getReceiveMsgMethod) == null) {
          ChatServiceGrpc.getReceiveMsgMethod = getReceiveMsgMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.ReceiveMessageRequest, com.grpc.protoCompiled.Messaging.ChatMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "receiveMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.ReceiveMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.ChatMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("receiveMsg"))
                  .build();
          }
        }
     }
     return getReceiveMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest,
      com.grpc.protoCompiled.Messaging.Empty> getStopReceiveMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "stopReceiveMsg",
      requestType = com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest,
      com.grpc.protoCompiled.Messaging.Empty> getStopReceiveMsgMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest, com.grpc.protoCompiled.Messaging.Empty> getStopReceiveMsgMethod;
    if ((getStopReceiveMsgMethod = ChatServiceGrpc.getStopReceiveMsgMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getStopReceiveMsgMethod = ChatServiceGrpc.getStopReceiveMsgMethod) == null) {
          ChatServiceGrpc.getStopReceiveMsgMethod = getStopReceiveMsgMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest, com.grpc.protoCompiled.Messaging.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "stopReceiveMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("stopReceiveMsg"))
                  .build();
          }
        }
     }
     return getStopReceiveMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.Empty,
      com.grpc.protoCompiled.Messaging.User> getGetAllUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllUsers",
      requestType = com.grpc.protoCompiled.Messaging.Empty.class,
      responseType = com.grpc.protoCompiled.Messaging.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.Empty,
      com.grpc.protoCompiled.Messaging.User> getGetAllUsersMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.Empty, com.grpc.protoCompiled.Messaging.User> getGetAllUsersMethod;
    if ((getGetAllUsersMethod = ChatServiceGrpc.getGetAllUsersMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getGetAllUsersMethod = ChatServiceGrpc.getGetAllUsersMethod) == null) {
          ChatServiceGrpc.getGetAllUsersMethod = getGetAllUsersMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.Empty, com.grpc.protoCompiled.Messaging.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "getAllUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.User.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("getAllUsers"))
                  .build();
          }
        }
     }
     return getGetAllUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.Empty,
      com.grpc.protoCompiled.Messaging.Group> getGetAllGroupsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllGroups",
      requestType = com.grpc.protoCompiled.Messaging.Empty.class,
      responseType = com.grpc.protoCompiled.Messaging.Group.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.Empty,
      com.grpc.protoCompiled.Messaging.Group> getGetAllGroupsMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.Empty, com.grpc.protoCompiled.Messaging.Group> getGetAllGroupsMethod;
    if ((getGetAllGroupsMethod = ChatServiceGrpc.getGetAllGroupsMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getGetAllGroupsMethod = ChatServiceGrpc.getGetAllGroupsMethod) == null) {
          ChatServiceGrpc.getGetAllGroupsMethod = getGetAllGroupsMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.Empty, com.grpc.protoCompiled.Messaging.Group>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "getAllGroups"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Group.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("getAllGroups"))
                  .build();
          }
        }
     }
     return getGetAllGroupsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SearchUserRequest,
      com.grpc.protoCompiled.Messaging.User> getSearchUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "searchUser",
      requestType = com.grpc.protoCompiled.Messaging.SearchUserRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SearchUserRequest,
      com.grpc.protoCompiled.Messaging.User> getSearchUserMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SearchUserRequest, com.grpc.protoCompiled.Messaging.User> getSearchUserMethod;
    if ((getSearchUserMethod = ChatServiceGrpc.getSearchUserMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSearchUserMethod = ChatServiceGrpc.getSearchUserMethod) == null) {
          ChatServiceGrpc.getSearchUserMethod = getSearchUserMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.SearchUserRequest, com.grpc.protoCompiled.Messaging.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "searchUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.SearchUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.User.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("searchUser"))
                  .build();
          }
        }
     }
     return getSearchUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SearchGroupRequest,
      com.grpc.protoCompiled.Messaging.Group> getSearchGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "searchGroup",
      requestType = com.grpc.protoCompiled.Messaging.SearchGroupRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.Group.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SearchGroupRequest,
      com.grpc.protoCompiled.Messaging.Group> getSearchGroupMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SearchGroupRequest, com.grpc.protoCompiled.Messaging.Group> getSearchGroupMethod;
    if ((getSearchGroupMethod = ChatServiceGrpc.getSearchGroupMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSearchGroupMethod = ChatServiceGrpc.getSearchGroupMethod) == null) {
          ChatServiceGrpc.getSearchGroupMethod = getSearchGroupMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.SearchGroupRequest, com.grpc.protoCompiled.Messaging.Group>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "searchGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.SearchGroupRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Group.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("searchGroup"))
                  .build();
          }
        }
     }
     return getSearchGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ChannelChat,
      com.grpc.protoCompiled.Messaging.User> getGetChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getChannel",
      requestType = com.grpc.protoCompiled.Messaging.ChannelChat.class,
      responseType = com.grpc.protoCompiled.Messaging.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ChannelChat,
      com.grpc.protoCompiled.Messaging.User> getGetChannelMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.ChannelChat, com.grpc.protoCompiled.Messaging.User> getGetChannelMethod;
    if ((getGetChannelMethod = ChatServiceGrpc.getGetChannelMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getGetChannelMethod = ChatServiceGrpc.getGetChannelMethod) == null) {
          ChatServiceGrpc.getGetChannelMethod = getGetChannelMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.ChannelChat, com.grpc.protoCompiled.Messaging.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "getChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.ChannelChat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.User.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("getChannel"))
                  .build();
          }
        }
     }
     return getGetChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User,
      com.grpc.protoCompiled.Messaging.ChannelChat> getGetConnectedChannelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getConnectedChannel",
      requestType = com.grpc.protoCompiled.Messaging.User.class,
      responseType = com.grpc.protoCompiled.Messaging.ChannelChat.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User,
      com.grpc.protoCompiled.Messaging.ChannelChat> getGetConnectedChannelMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User, com.grpc.protoCompiled.Messaging.ChannelChat> getGetConnectedChannelMethod;
    if ((getGetConnectedChannelMethod = ChatServiceGrpc.getGetConnectedChannelMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getGetConnectedChannelMethod = ChatServiceGrpc.getGetConnectedChannelMethod) == null) {
          ChatServiceGrpc.getGetConnectedChannelMethod = getGetConnectedChannelMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.User, com.grpc.protoCompiled.Messaging.ChannelChat>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "getConnectedChannel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.ChannelChat.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("getConnectedChannel"))
                  .build();
          }
        }
     }
     return getGetConnectedChannelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SetStatusRequest,
      com.grpc.protoCompiled.Messaging.Empty> getSetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setStatus",
      requestType = com.grpc.protoCompiled.Messaging.SetStatusRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SetStatusRequest,
      com.grpc.protoCompiled.Messaging.Empty> getSetStatusMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.SetStatusRequest, com.grpc.protoCompiled.Messaging.Empty> getSetStatusMethod;
    if ((getSetStatusMethod = ChatServiceGrpc.getSetStatusMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSetStatusMethod = ChatServiceGrpc.getSetStatusMethod) == null) {
          ChatServiceGrpc.getSetStatusMethod = getSetStatusMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.SetStatusRequest, com.grpc.protoCompiled.Messaging.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "setStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.SetStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("setStatus"))
                  .build();
          }
        }
     }
     return getSetStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User,
      com.grpc.protoCompiled.Messaging.StatusResponse> getGetStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getStatus",
      requestType = com.grpc.protoCompiled.Messaging.User.class,
      responseType = com.grpc.protoCompiled.Messaging.StatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User,
      com.grpc.protoCompiled.Messaging.StatusResponse> getGetStatusMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.User, com.grpc.protoCompiled.Messaging.StatusResponse> getGetStatusMethod;
    if ((getGetStatusMethod = ChatServiceGrpc.getGetStatusMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getGetStatusMethod = ChatServiceGrpc.getGetStatusMethod) == null) {
          ChatServiceGrpc.getGetStatusMethod = getGetStatusMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.User, com.grpc.protoCompiled.Messaging.StatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "getStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.StatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("getStatus"))
                  .build();
          }
        }
     }
     return getGetStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.FindUserRequest,
      com.grpc.protoCompiled.Messaging.User> getFindUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findUser",
      requestType = com.grpc.protoCompiled.Messaging.FindUserRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.FindUserRequest,
      com.grpc.protoCompiled.Messaging.User> getFindUserMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.FindUserRequest, com.grpc.protoCompiled.Messaging.User> getFindUserMethod;
    if ((getFindUserMethod = ChatServiceGrpc.getFindUserMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getFindUserMethod = ChatServiceGrpc.getFindUserMethod) == null) {
          ChatServiceGrpc.getFindUserMethod = getFindUserMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.FindUserRequest, com.grpc.protoCompiled.Messaging.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "findUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.FindUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.User.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("findUser"))
                  .build();
          }
        }
     }
     return getFindUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.FindGroupRequest,
      com.grpc.protoCompiled.Messaging.Group> getFindGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findGroup",
      requestType = com.grpc.protoCompiled.Messaging.FindGroupRequest.class,
      responseType = com.grpc.protoCompiled.Messaging.Group.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.FindGroupRequest,
      com.grpc.protoCompiled.Messaging.Group> getFindGroupMethod() {
    io.grpc.MethodDescriptor<com.grpc.protoCompiled.Messaging.FindGroupRequest, com.grpc.protoCompiled.Messaging.Group> getFindGroupMethod;
    if ((getFindGroupMethod = ChatServiceGrpc.getFindGroupMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getFindGroupMethod = ChatServiceGrpc.getFindGroupMethod) == null) {
          ChatServiceGrpc.getFindGroupMethod = getFindGroupMethod = 
              io.grpc.MethodDescriptor.<com.grpc.protoCompiled.Messaging.FindGroupRequest, com.grpc.protoCompiled.Messaging.Group>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "chat.grpc.ChatService", "findGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.FindGroupRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.protoCompiled.Messaging.Group.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("findGroup"))
                  .build();
          }
        }
     }
     return getFindGroupMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    return new ChatServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void join(com.grpc.protoCompiled.Messaging.User request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.JoinResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinMethod(), responseObserver);
    }

    /**
     */
    public void joinChannel(com.grpc.protoCompiled.Messaging.JoinChannelRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChannelChat> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinChannelMethod(), responseObserver);
    }

    /**
     */
    public void connectToChannel(com.grpc.protoCompiled.Messaging.ConnectChannelRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getConnectToChannelMethod(), responseObserver);
    }

    /**
     */
    public void disconnectToChannel(com.grpc.protoCompiled.Messaging.DisconnectChannelRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getDisconnectToChannelMethod(), responseObserver);
    }

    /**
     */
    public void sendMsg(com.grpc.protoCompiled.Messaging.SendMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMsgMethod(), responseObserver);
    }

    /**
     */
    public void receiveMsg(com.grpc.protoCompiled.Messaging.ReceiveMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChatMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveMsgMethod(), responseObserver);
    }

    /**
     */
    public void stopReceiveMsg(com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getStopReceiveMsgMethod(), responseObserver);
    }

    /**
     */
    public void getAllUsers(com.grpc.protoCompiled.Messaging.Empty request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllUsersMethod(), responseObserver);
    }

    /**
     */
    public void getAllGroups(com.grpc.protoCompiled.Messaging.Empty request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllGroupsMethod(), responseObserver);
    }

    /**
     */
    public void searchUser(com.grpc.protoCompiled.Messaging.SearchUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchUserMethod(), responseObserver);
    }

    /**
     */
    public void searchGroup(com.grpc.protoCompiled.Messaging.SearchGroupRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchGroupMethod(), responseObserver);
    }

    /**
     */
    public void getChannel(com.grpc.protoCompiled.Messaging.ChannelChat request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User> responseObserver) {
      asyncUnimplementedUnaryCall(getGetChannelMethod(), responseObserver);
    }

    /**
     */
    public void getConnectedChannel(com.grpc.protoCompiled.Messaging.User request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChannelChat> responseObserver) {
      asyncUnimplementedUnaryCall(getGetConnectedChannelMethod(), responseObserver);
    }

    /**
     */
    public void setStatus(com.grpc.protoCompiled.Messaging.SetStatusRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSetStatusMethod(), responseObserver);
    }

    /**
     */
    public void getStatus(com.grpc.protoCompiled.Messaging.User request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.StatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStatusMethod(), responseObserver);
    }

    /**
     */
    public void findUser(com.grpc.protoCompiled.Messaging.FindUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User> responseObserver) {
      asyncUnimplementedUnaryCall(getFindUserMethod(), responseObserver);
    }

    /**
     */
    public void findGroup(com.grpc.protoCompiled.Messaging.FindGroupRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group> responseObserver) {
      asyncUnimplementedUnaryCall(getFindGroupMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getJoinMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.User,
                com.grpc.protoCompiled.Messaging.JoinResponse>(
                  this, METHODID_JOIN)))
          .addMethod(
            getJoinChannelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.JoinChannelRequest,
                com.grpc.protoCompiled.Messaging.ChannelChat>(
                  this, METHODID_JOIN_CHANNEL)))
          .addMethod(
            getConnectToChannelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.ConnectChannelRequest,
                com.grpc.protoCompiled.Messaging.Empty>(
                  this, METHODID_CONNECT_TO_CHANNEL)))
          .addMethod(
            getDisconnectToChannelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.DisconnectChannelRequest,
                com.grpc.protoCompiled.Messaging.Empty>(
                  this, METHODID_DISCONNECT_TO_CHANNEL)))
          .addMethod(
            getSendMsgMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.SendMessageRequest,
                com.grpc.protoCompiled.Messaging.Empty>(
                  this, METHODID_SEND_MSG)))
          .addMethod(
            getReceiveMsgMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.ReceiveMessageRequest,
                com.grpc.protoCompiled.Messaging.ChatMessage>(
                  this, METHODID_RECEIVE_MSG)))
          .addMethod(
            getStopReceiveMsgMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest,
                com.grpc.protoCompiled.Messaging.Empty>(
                  this, METHODID_STOP_RECEIVE_MSG)))
          .addMethod(
            getGetAllUsersMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.Empty,
                com.grpc.protoCompiled.Messaging.User>(
                  this, METHODID_GET_ALL_USERS)))
          .addMethod(
            getGetAllGroupsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.Empty,
                com.grpc.protoCompiled.Messaging.Group>(
                  this, METHODID_GET_ALL_GROUPS)))
          .addMethod(
            getSearchUserMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.SearchUserRequest,
                com.grpc.protoCompiled.Messaging.User>(
                  this, METHODID_SEARCH_USER)))
          .addMethod(
            getSearchGroupMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.SearchGroupRequest,
                com.grpc.protoCompiled.Messaging.Group>(
                  this, METHODID_SEARCH_GROUP)))
          .addMethod(
            getGetChannelMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.ChannelChat,
                com.grpc.protoCompiled.Messaging.User>(
                  this, METHODID_GET_CHANNEL)))
          .addMethod(
            getGetConnectedChannelMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.User,
                com.grpc.protoCompiled.Messaging.ChannelChat>(
                  this, METHODID_GET_CONNECTED_CHANNEL)))
          .addMethod(
            getSetStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.SetStatusRequest,
                com.grpc.protoCompiled.Messaging.Empty>(
                  this, METHODID_SET_STATUS)))
          .addMethod(
            getGetStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.User,
                com.grpc.protoCompiled.Messaging.StatusResponse>(
                  this, METHODID_GET_STATUS)))
          .addMethod(
            getFindUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.FindUserRequest,
                com.grpc.protoCompiled.Messaging.User>(
                  this, METHODID_FIND_USER)))
          .addMethod(
            getFindGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.protoCompiled.Messaging.FindGroupRequest,
                com.grpc.protoCompiled.Messaging.Group>(
                  this, METHODID_FIND_GROUP)))
          .build();
    }
  }

  /**
   */
  public static final class ChatServiceStub extends io.grpc.stub.AbstractStub<ChatServiceStub> {
    private ChatServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    /**
     */
    public void join(com.grpc.protoCompiled.Messaging.User request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.JoinResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinChannel(com.grpc.protoCompiled.Messaging.JoinChannelRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChannelChat> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void connectToChannel(com.grpc.protoCompiled.Messaging.ConnectChannelRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConnectToChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void disconnectToChannel(com.grpc.protoCompiled.Messaging.DisconnectChannelRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDisconnectToChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMsg(com.grpc.protoCompiled.Messaging.SendMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveMsg(com.grpc.protoCompiled.Messaging.ReceiveMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChatMessage> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stopReceiveMsg(com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStopReceiveMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllUsers(com.grpc.protoCompiled.Messaging.Empty request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetAllUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllGroups(com.grpc.protoCompiled.Messaging.Empty request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetAllGroupsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchUser(com.grpc.protoCompiled.Messaging.SearchUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSearchUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchGroup(com.grpc.protoCompiled.Messaging.SearchGroupRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSearchGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getChannel(com.grpc.protoCompiled.Messaging.ChannelChat request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getConnectedChannel(com.grpc.protoCompiled.Messaging.User request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChannelChat> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetConnectedChannelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setStatus(com.grpc.protoCompiled.Messaging.SetStatusRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(com.grpc.protoCompiled.Messaging.User request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.StatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findUser(com.grpc.protoCompiled.Messaging.FindUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findGroup(com.grpc.protoCompiled.Messaging.FindGroupRequest request,
        io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindGroupMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChatServiceBlockingStub extends io.grpc.stub.AbstractStub<ChatServiceBlockingStub> {
    private ChatServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.JoinResponse join(com.grpc.protoCompiled.Messaging.User request) {
      return blockingUnaryCall(
          getChannel(), getJoinMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.ChannelChat joinChannel(com.grpc.protoCompiled.Messaging.JoinChannelRequest request) {
      return blockingUnaryCall(
          getChannel(), getJoinChannelMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.Empty connectToChannel(com.grpc.protoCompiled.Messaging.ConnectChannelRequest request) {
      return blockingUnaryCall(
          getChannel(), getConnectToChannelMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.Empty disconnectToChannel(com.grpc.protoCompiled.Messaging.DisconnectChannelRequest request) {
      return blockingUnaryCall(
          getChannel(), getDisconnectToChannelMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.Empty sendMsg(com.grpc.protoCompiled.Messaging.SendMessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendMsgMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.grpc.protoCompiled.Messaging.ChatMessage> receiveMsg(
        com.grpc.protoCompiled.Messaging.ReceiveMessageRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveMsgMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.Empty stopReceiveMsg(com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getStopReceiveMsgMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.grpc.protoCompiled.Messaging.User> getAllUsers(
        com.grpc.protoCompiled.Messaging.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getGetAllUsersMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.grpc.protoCompiled.Messaging.Group> getAllGroups(
        com.grpc.protoCompiled.Messaging.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getGetAllGroupsMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.grpc.protoCompiled.Messaging.User> searchUser(
        com.grpc.protoCompiled.Messaging.SearchUserRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSearchUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.grpc.protoCompiled.Messaging.Group> searchGroup(
        com.grpc.protoCompiled.Messaging.SearchGroupRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSearchGroupMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.grpc.protoCompiled.Messaging.User> getChannel(
        com.grpc.protoCompiled.Messaging.ChannelChat request) {
      return blockingServerStreamingCall(
          getChannel(), getGetChannelMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.grpc.protoCompiled.Messaging.ChannelChat> getConnectedChannel(
        com.grpc.protoCompiled.Messaging.User request) {
      return blockingServerStreamingCall(
          getChannel(), getGetConnectedChannelMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.Empty setStatus(com.grpc.protoCompiled.Messaging.SetStatusRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.StatusResponse getStatus(com.grpc.protoCompiled.Messaging.User request) {
      return blockingUnaryCall(
          getChannel(), getGetStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.User findUser(com.grpc.protoCompiled.Messaging.FindUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.protoCompiled.Messaging.Group findGroup(com.grpc.protoCompiled.Messaging.FindGroupRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindGroupMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChatServiceFutureStub extends io.grpc.stub.AbstractStub<ChatServiceFutureStub> {
    private ChatServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.JoinResponse> join(
        com.grpc.protoCompiled.Messaging.User request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.ChannelChat> joinChannel(
        com.grpc.protoCompiled.Messaging.JoinChannelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinChannelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.Empty> connectToChannel(
        com.grpc.protoCompiled.Messaging.ConnectChannelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConnectToChannelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.Empty> disconnectToChannel(
        com.grpc.protoCompiled.Messaging.DisconnectChannelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDisconnectToChannelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.Empty> sendMsg(
        com.grpc.protoCompiled.Messaging.SendMessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMsgMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.Empty> stopReceiveMsg(
        com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStopReceiveMsgMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.Empty> setStatus(
        com.grpc.protoCompiled.Messaging.SetStatusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.StatusResponse> getStatus(
        com.grpc.protoCompiled.Messaging.User request) {
      return futureUnaryCall(
          getChannel().newCall(getGetStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.User> findUser(
        com.grpc.protoCompiled.Messaging.FindUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.protoCompiled.Messaging.Group> findGroup(
        com.grpc.protoCompiled.Messaging.FindGroupRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindGroupMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_JOIN = 0;
  private static final int METHODID_JOIN_CHANNEL = 1;
  private static final int METHODID_CONNECT_TO_CHANNEL = 2;
  private static final int METHODID_DISCONNECT_TO_CHANNEL = 3;
  private static final int METHODID_SEND_MSG = 4;
  private static final int METHODID_RECEIVE_MSG = 5;
  private static final int METHODID_STOP_RECEIVE_MSG = 6;
  private static final int METHODID_GET_ALL_USERS = 7;
  private static final int METHODID_GET_ALL_GROUPS = 8;
  private static final int METHODID_SEARCH_USER = 9;
  private static final int METHODID_SEARCH_GROUP = 10;
  private static final int METHODID_GET_CHANNEL = 11;
  private static final int METHODID_GET_CONNECTED_CHANNEL = 12;
  private static final int METHODID_SET_STATUS = 13;
  private static final int METHODID_GET_STATUS = 14;
  private static final int METHODID_FIND_USER = 15;
  private static final int METHODID_FIND_GROUP = 16;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_JOIN:
          serviceImpl.join((com.grpc.protoCompiled.Messaging.User) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.JoinResponse>) responseObserver);
          break;
        case METHODID_JOIN_CHANNEL:
          serviceImpl.joinChannel((com.grpc.protoCompiled.Messaging.JoinChannelRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChannelChat>) responseObserver);
          break;
        case METHODID_CONNECT_TO_CHANNEL:
          serviceImpl.connectToChannel((com.grpc.protoCompiled.Messaging.ConnectChannelRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty>) responseObserver);
          break;
        case METHODID_DISCONNECT_TO_CHANNEL:
          serviceImpl.disconnectToChannel((com.grpc.protoCompiled.Messaging.DisconnectChannelRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty>) responseObserver);
          break;
        case METHODID_SEND_MSG:
          serviceImpl.sendMsg((com.grpc.protoCompiled.Messaging.SendMessageRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_MSG:
          serviceImpl.receiveMsg((com.grpc.protoCompiled.Messaging.ReceiveMessageRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChatMessage>) responseObserver);
          break;
        case METHODID_STOP_RECEIVE_MSG:
          serviceImpl.stopReceiveMsg((com.grpc.protoCompiled.Messaging.StopReceiveMessageRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty>) responseObserver);
          break;
        case METHODID_GET_ALL_USERS:
          serviceImpl.getAllUsers((com.grpc.protoCompiled.Messaging.Empty) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User>) responseObserver);
          break;
        case METHODID_GET_ALL_GROUPS:
          serviceImpl.getAllGroups((com.grpc.protoCompiled.Messaging.Empty) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group>) responseObserver);
          break;
        case METHODID_SEARCH_USER:
          serviceImpl.searchUser((com.grpc.protoCompiled.Messaging.SearchUserRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User>) responseObserver);
          break;
        case METHODID_SEARCH_GROUP:
          serviceImpl.searchGroup((com.grpc.protoCompiled.Messaging.SearchGroupRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group>) responseObserver);
          break;
        case METHODID_GET_CHANNEL:
          serviceImpl.getChannel((com.grpc.protoCompiled.Messaging.ChannelChat) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User>) responseObserver);
          break;
        case METHODID_GET_CONNECTED_CHANNEL:
          serviceImpl.getConnectedChannel((com.grpc.protoCompiled.Messaging.User) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.ChannelChat>) responseObserver);
          break;
        case METHODID_SET_STATUS:
          serviceImpl.setStatus((com.grpc.protoCompiled.Messaging.SetStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Empty>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((com.grpc.protoCompiled.Messaging.User) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.StatusResponse>) responseObserver);
          break;
        case METHODID_FIND_USER:
          serviceImpl.findUser((com.grpc.protoCompiled.Messaging.FindUserRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.User>) responseObserver);
          break;
        case METHODID_FIND_GROUP:
          serviceImpl.findGroup((com.grpc.protoCompiled.Messaging.FindGroupRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.protoCompiled.Messaging.Group>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.grpc.Service.proto.ChatServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatService");
    }
  }

  private static final class ChatServiceFileDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier {
    ChatServiceFileDescriptorSupplier() {}
  }

  private static final class ChatServiceMethodDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatServiceFileDescriptorSupplier())
              .addMethod(getJoinMethod())
              .addMethod(getJoinChannelMethod())
              .addMethod(getConnectToChannelMethod())
              .addMethod(getDisconnectToChannelMethod())
              .addMethod(getSendMsgMethod())
              .addMethod(getReceiveMsgMethod())
              .addMethod(getStopReceiveMsgMethod())
              .addMethod(getGetAllUsersMethod())
              .addMethod(getGetAllGroupsMethod())
              .addMethod(getSearchUserMethod())
              .addMethod(getSearchGroupMethod())
              .addMethod(getGetChannelMethod())
              .addMethod(getGetConnectedChannelMethod())
              .addMethod(getSetStatusMethod())
              .addMethod(getGetStatusMethod())
              .addMethod(getFindUserMethod())
              .addMethod(getFindGroupMethod())
              .build();
        }
      }
    }
    return result;
  }
}
