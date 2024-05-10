# Chat Server with GRPC
**Creator : <br>
Aissi Mohamed Hedi (Software engineering Student -FST )**<br>


## Problem Statement
In this project, we will build a simplified chat server that can support multiple clients at the same time. We are (sort of) going to mimic popular chat apps like Slack, Discord or even Messenger.

## Prerequities
- Have a Java installed JDK with a 17 version or Higher
- Have a Redis Server Installed on your machine<br>
To run Redis you need to install it on Linux or WSL and start the service using : 
```
	sudo service redis-server start
```
To stop the Redis server you type :  
```
	sudo service redis-server stop
```
Here is a simple list of commands and behaviors I want your chat server to implement:<br>
- [ ] `join`  A user can join the platform .
- [ ] `joinChannel` for joining a channel if it is public or creating a channel with another user(private channel)
- [ ] `send`  Send a message to a Group.
- [ ] `receive` <channel> Receive continiously messages from a channel
- [ ] `StopReceive` <channel> Stop receiving messages from a channel
- [ ] `Connect` Connect to one channel to be able to communicate .
- [ ] `Disconnect` disconnect from a channel
- [ ] `Block` block a user from a group
- [ ] `Add` adds a user to a group to have access to a common multicast channel.
- [ ] `AddAdmin` adds a user to a group as admin.

### Functionnalities:
- Develop a simple text-based client to demonstrate the functionality of you chat server.
- A client must have joined the platfrom to be able to communnicate with the others.
- A client must have joined a channel to receive or send messages using the channel. Plus, it must be connected so it is possible to exchange messages.
- A user can have access to multiple channels at the same time .
- A user can search for groups and other users to join or create a channel with them.
- A message sent to a channel must be delivered to every client that is part of the channel.
- When receiving a message , the client keeps recceiving it until a stop RPC is launched.
- The server can support mutiple concurrent users.
- An admin can manage its group easily (CRUD ...).
- Clean up unused connections and sockets. Not doing so creates resources leaks that may cause long running servers to crash.
- Shut down the server gracefully after it has been idle for a period of time.
- Add a shutdown hook to gracefully stop the server if it receives an interrupt signal.
- Persistance is not necessary. It’s perfectly fine to comepletely restart if the server is killed.
- Added a request timeout handling procedure so the server can notice when the client context is out .
- Added a very stuctured and clear ways to handle errors and deadline.
- Implement gRPC status code to make things clear for client .



## Project Architecture
The structure of the project is below :<br/>
&nbsp; &nbsp;   --`java.com.grpc`: The whole project folder conatining all the files .<br />
&nbsp; &nbsp;    |<br />
&nbsp; &nbsp;    |--`proto`: contains the Proto files for the Services and the Messages.<br />
&nbsp; &nbsp;    |<br />
&nbsp; &nbsp;    |--`Service`: contains the Java code for Server , Client , Channel , User and Group services and DAO , In-Memory store for all the data and Generator for Testing purpose .<br />
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |<br />
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |--`proto`: contains the generated code for the proto files in the proto code repository.<br/>
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |<br/>
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |--`Channel`: contains the In-memeory store  for the  code for the Channels.<br/>   
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |<br />
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |--`Client`: A CLient for the server implemented in Java.<br/>
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |<br/>
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |--`Server`: contains the Chat server code in java .<br/>
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |<br />
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |--`dao`: contains data access objects for User and Group.<br/>
&nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;      |<br/>
Below image explains the implementation of Server and Client.

<img src="https://i.ibb.co/4tvLTP2/lol.png" alt="Architecture" border="0">

## Dependencies

We have included all the dependecies required in `pom.xml` as below. You wouldn't need to make any changes in the `pom.xml` to run the project from the terminal.

```
    <dependencies>
		<dependency>
			<groupId>com.google.protobuf</groupId>
			<artifactId>protobuf-java</artifactId>
			<version>3.6.1</version>
		</dependency>
		<dependency>
			<groupId>javax.annotation</groupId>
			<artifactId>javax.annotation-api</artifactId>
			<version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>io.grpc</groupId>
			<artifactId>grpc-netty-shaded</artifactId>
			<version>1.15.1</version>
		</dependency>
		<dependency>
			<groupId>io.grpc</groupId>
			<artifactId>grpc-protobuf</artifactId>
			<version>1.15.1</version>
		</dependency>
		<dependency>
			<groupId>io.grpc</groupId>
			<artifactId>grpc-stub</artifactId>
			<version>1.15.1</version>
		</dependency>
	</dependencies>
```

## Instructions to execute code
### Server Execution

1. Please open Windows Command Prompt(cmd) or Terminal on Mac.
2. Change the directory to the project `Messagery`.
```
cd Messagery
```
3. Install dependancies and Generate Java code for Proto Files.
```
mvn install
```
4. Create the jar file with all the dependancies in the same package .
```
mvn package
```
5. or to  clean , intall and build, you may use below instructions as well
```
mvn clean package install  (Preferably)
```
6. To run Server:
```
java -cp target/messaging-1.0-jar-with-dependencies.jar com/grpc/Service/Server/ChatServer
```



### Client Execution
1. Please open Windows Command Prompt(cmd) or Terminal on Mac.
2. Then please package the Jar file using the plugin `maven-assembly-plugin`if you did not do the steps 5-6 in the Server Execution.
3. To run Client:
 ```
java -cp target/messaging-1.0-jar-with-dependencies.jar com/grpc/Service/Client/ChatClientJava
```



To run multiple Clients, you may repeat step 3 in different Command Prompts .
