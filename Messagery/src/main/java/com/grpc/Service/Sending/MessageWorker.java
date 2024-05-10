package com.grpc.Service.Sending;


import com.google.protobuf.Timestamp;
import com.grpc.Service.ChatStream;
import com.grpc.protoCompiled.Messaging.ChatMessage;

public class MessageWorker implements Comparable<MessageWorker>{
    private ChatMessage Message ; 
    private ChatStream<ChatMessage> Stream ; 
    

    public MessageWorker(ChatMessage _Message ,ChatStream<ChatMessage> _Stream ){
        Message = _Message;
        Stream = _Stream;
    }


    public void setStream(ChatStream<ChatMessage> stream) {
        Stream = stream;
    }
    public void setMessage(ChatMessage message) {
        Message = message;
    }
    public ChatMessage getMessage() {
        return Message;
    }
    public ChatStream<ChatMessage> getStream() {
        return Stream;
    }


    @Override
    public int compareTo(MessageWorker arg0) {
        Timestamp time0 = arg0.getMessage().getTime();
        Timestamp time = this.Message.getTime();
        if(time.getSeconds()>time0.getSeconds()){
            return 1;
        }
        else if(time.getSeconds()<time0.getSeconds()){
            return -1;
        }
        else{
            if(time.getNanos()>time0.getNanos()){
                return 1;
            }
            else if(time.getNanos()<time0.getNanos()){
                return -1;
            }
            else {
                return 1;
            }
        }
    }
} 
