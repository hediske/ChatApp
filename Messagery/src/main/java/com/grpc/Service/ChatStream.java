package com.grpc.Service;

public interface ChatStream<T> {
    
   public  void send(T t);
}
