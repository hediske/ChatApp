// package com.grpc.Service;

// import java.util.concurrent.PriorityBlockingQueue;

// import com.grpc.Service.Sending.MessageWorker;
// import com.grpc.Service.Sending.executorMessage;
// import com.grpc.proto.Messaging.ChatMessage;


// public class sendingService {
//          private static PriorityBlockingQueue<MessageWorker> MessageList = new PriorityBlockingQueue<>(150);
//         private static  executorMessage executors = new executorMessage(MessageList);

//         public static void lauchWorkers(){
//             executors.LaunchExecutor();
//         }
//         public static void shutdown(){
//             executors.shutdown();
//         }

//         public static void addMessage( ChatMessage message , ChatStream<ChatMessage> stream ){

//             MessageWorker messagetoreceive;
//             messagetoreceive =  new MessageWorker(message,stream);
//             System.out.println(":)  the message "+message.getMsg()+ " added to the pool" );
//             MessageList.add(messagetoreceive);
//             }
// }
