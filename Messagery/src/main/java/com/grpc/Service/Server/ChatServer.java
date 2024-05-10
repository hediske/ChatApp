package com.grpc.Service.Server;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.grpc.Service.chatservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ChatServer {
    private final int port;
    private final Server server;
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    public ChatServer(int port,chatservice chatservice ){
        this(ServerBuilder.forPort(port),port,chatservice);
    }
    public ChatServer(ServerBuilder serverBuilder ,int port,chatservice chatservice ){
        this.port=port;
        this.server = serverBuilder.addService(chatservice).build();
        
    }
    public void start() throws IOException{
        this.server.start();
        logger.info("Server is launched on port "+this.port+" ! ....");

        // case when the JVM is interrupted   !!
        Runtime.getRuntime().addShutdownHook(new Thread(){
        @Override
        public void run(){
            System.err.println("server Grpc can not work as JVM is interrupted  !! ");
            try{
                ChatServer.this.stop();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            System.err.println("Server shutownn due to JVM interruption Signal.");
        }
        }
        );
    }

    public void stop() throws InterruptedException{
        if(server!=null){
            this.server.awaitTermination(15,TimeUnit.SECONDS);
            logger.info("server shutdown  .... ");
        }
    }

    //block main method when JVM is shutdown (Daemon thread for grpc server (working after the jvm is closed like garbage collector))
    public void blockMainWhenJVMisTerminated() throws InterruptedException{
        if(server!=null){
            server.awaitTermination();
        }
    }


    public static void main(String[] args) throws InterruptedException, IOException{
        chatservice service = new chatservice();
        ChatServer server = new ChatServer(8080,service);
        server.start();
        server.blockMainWhenJVMisTerminated();
    }
}
