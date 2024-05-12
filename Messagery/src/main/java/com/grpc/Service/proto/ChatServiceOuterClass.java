// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chat-service.proto

package com.grpc.Service.proto;

public final class ChatServiceOuterClass {
  private ChatServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022chat-service.proto\022\tchat.grpc\032\022chat-me" +
      "ssage.proto2\344\007\n\013ChatService\0222\n\004join\022\017.ch" +
      "at.grpc.User\032\027.chat.grpc.JoinResponse\"\000\022" +
      "F\n\013joinChannel\022\035.chat.grpc.JoinChannelRe" +
      "quest\032\026.chat.grpc.ChannelChat\"\000\022H\n\020conne" +
      "ctToChannel\022 .chat.grpc.ConnectChannelRe" +
      "quest\032\020.chat.grpc.Empty\"\000\022N\n\023disconnectT" +
      "oChannel\022#.chat.grpc.DisconnectChannelRe" +
      "quest\032\020.chat.grpc.Empty\"\000\022<\n\007sendMsg\022\035.c" +
      "hat.grpc.SendMessageRequest\032\020.chat.grpc." +
      "Empty\"\000\022J\n\nreceiveMsg\022 .chat.grpc.Receiv" +
      "eMessageRequest\032\026.chat.grpc.ChatMessage\"" +
      "\0000\001\022J\n\016stopReceiveMsg\022$.chat.grpc.StopRe" +
      "ceiveMessageRequest\032\020.chat.grpc.Empty\"\000\022" +
      "4\n\013getAllUsers\022\020.chat.grpc.Empty\032\017.chat." +
      "grpc.User\"\0000\001\0226\n\014getAllGroups\022\020.chat.grp" +
      "c.Empty\032\020.chat.grpc.Group\"\0000\001\022?\n\nsearchU" +
      "ser\022\034.chat.grpc.SearchUserRequest\032\017.chat" +
      ".grpc.User\"\0000\001\022B\n\013searchGroup\022\035.chat.grp" +
      "c.SearchGroupRequest\032\020.chat.grpc.Group\"\000" +
      "0\001\0229\n\ngetChannel\022\026.chat.grpc.ChannelChat" +
      "\032\017.chat.grpc.User\"\0000\001\022B\n\023getConnectedCha" +
      "nnel\022\017.chat.grpc.User\032\026.chat.grpc.Channe" +
      "lChat\"\0000\001\022<\n\tsetStatus\022\033.chat.grpc.SetSt" +
      "atusRequest\032\020.chat.grpc.Empty\"\000\0229\n\tgetSt" +
      "atus\022\017.chat.grpc.User\032\031.chat.grpc.Status" +
      "Response\"\000B\032\n\026com.grpc.Service.protoP\001b\006" +
      "proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.grpc.protoCompiled.Messaging.ChatMessageOuterClass.getDescriptor(),
        }, assigner);
    com.grpc.protoCompiled.Messaging.ChatMessageOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
