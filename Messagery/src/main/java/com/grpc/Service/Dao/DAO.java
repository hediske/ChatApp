package com.grpc.Service.Dao;

import java.util.Optional;

import com.grpc.Service.ChatStream;


public interface DAO<T,K> {
    Optional<T> find(String id) throws Exception;
    void getAll(ChatStream<T> stream) throws Exception;
    void save(T t) throws Exception;
    void update(T t,String ... params) throws Exception;
    void delete(T t) throws Exception;
    void find(K filter, ChatStream<T> stream)throws Exception;
}
