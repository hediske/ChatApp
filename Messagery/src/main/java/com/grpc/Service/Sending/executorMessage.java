package com.grpc.Service.Sending;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class executorMessage {
    private final static ExecutorService executor = Executors.newFixedThreadPool(16);
    private PriorityBlockingQueue<MessageWorker> MessageList ; 
    
    
    public executorMessage(PriorityBlockingQueue<MessageWorker> _MessageList){
        MessageList = _MessageList;
    }

    
    public void LaunchExecutor(){
        for (int i = 0; i < 16; i++) {
            executor.execute(new WorkerThread("TaskExecutor-" + i, MessageList , executor));
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
