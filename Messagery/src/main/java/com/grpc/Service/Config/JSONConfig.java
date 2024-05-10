package com.grpc.Service.Config;

import java.io.IOException;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Struct;
import com.google.protobuf.Struct.Builder;
import com.google.protobuf.util.JsonFormat;

public class JSONConfig {
    public static String toJson(MessageOrBuilder messageOrBuilder) throws IOException {
        return JsonFormat.printer().print(messageOrBuilder);
    }
    public static <T extends Message> void fromJson(String json, T.Builder builder) {
        try {
            JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            System.out.println("Error fetching the data from the Redis Database");
        }
      }
}
