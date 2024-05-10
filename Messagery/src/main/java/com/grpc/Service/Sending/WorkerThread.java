package com.grpc.Service.Sending;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;

public class WorkerThread implements Runnable{
    private PriorityBlockingQueue<MessageWorker> MessageList ; 
    private String name;
    private ExecutorService executorService;


    WorkerThread(String name , PriorityBlockingQueue< MessageWorker> MessageList, ExecutorService executorService){
        this.name = name;
        this.MessageList = MessageList;
        this.executorService = executorService;
    }


    @Override
    public void run() {
        while (true) {
            try{
                
                MessageWorker MessageTask = null;
                MessageTask =  MessageList.poll();
                if(MessageTask==null){
                    Thread.sleep(250);
                    
                }
                else{
                    System.out.println("thread "+name + " successfully treated "+MessageTask.getMessage().getMsg());
                    MessageTask.getStream().send(MessageTask.getMessage());
                }
                
            }
            catch(Exception e ){
                System.out.println("Exception occurred in worker thread " + this.name + ": " + e.getMessage());
                executorService.execute(new WorkerThread(name,MessageList,executorService));
                return;
            }
        }
    }



}
