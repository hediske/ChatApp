# Chat App - A Platform for real time messaging using React , GRRC ,JAVA , Envoy Proxy , Docker and Redis DB

## Technologies:
- React 
- Protobuf
- grpc
- docker
- Envoy
- Redis
- Java

## Prerequities 
- Java JDK installed with minimum version 17 and maven build tool.
- Running Docker Engine .
- React (npm or yarn installer).
- Protoc installed in your PATH to compile frontend proto files

## Features 
### To check the whole features you can check here. [LINK](/Messagery/Readme.md)

## How it works
- FrontEnd :
The frontEnd was build using React. A create-react-app project to test and put in action the different fonctionnalities by consuming a Java backend Server . A simple and cool UI enhancing the experience of the User .
Used grpc-web to compile  proto files into typescript files and create connection to the grpc based Java server and use the Rpc provided by the backend.The Application consists of a facade that allows the user to choose between *Chat Menu* , *Group Menu* or *User Menu* 
The Chat Menu has all the channels to whom the current user is connected and allows him to send and receive messages form those channels at the time .For the User Menu , A User can check all the connected and not connected Users at that time.To improve the performance , we added a cache configuration in frontend and also in back using Caffeine Java library for caching.

- BackEnd :
The Biggest part of the project was creating a stable and functionnal server based on grpc and using Redis DB for storage . The User , Groups , Channels data were stored in Key - values pairs after a Json Parsing from and To the proto format (Bitarray) . For the realtime communication , we used the Redis Pub/Sub for manipulating the messages in channels (group channels and Private Channels -DM) by publishing messages after parsing in JSON format , We setted up also a stream for handling the messages received after subscribing to be directly passed to the client for fast Messaging  . 
For the Redis integration in the backend server , we used Lettuce instead of Jedis because lettuce allows Reactive communication and building non-blocking servers .The goal of using Redis and Grpc is to establish a fast and reliable and reactive communication in the application for limited number of users at the same time.
We setted up also a logging and error handling mechanism for the server using Java Log4j2 to be able to monitor and debug the server.



## how to Run 
1- Start Envoy and Redis (need docker to be active first)
```
 docker compose up -d
 ```
2- starting the java server
```
cd Messaging
mvn clean package install 
java -cp target/messaging-1.0-jar-with-dependencies.jar com/grpc/Service/Server/ChatServer
```
3- starting the React server
```
cd ../chat-app-react
./proto-gen.sh  (run a script for proto files compile)
npm install --force
npm start
```
# Demo
https://github.com/hediske/ChatApp/assets/148924541/89bce51f-afa1-4f72-aa3e-f8018058e836

# Future Enhancement
We work on adding group features in The react app and why not secure the app by adding authentifcation ?

