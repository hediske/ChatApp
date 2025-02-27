import { ChannelChat, ChatMessage,Status, ConnectChannelRequest, DisconnectChannelRequest, Empty, Filter, JoinChannelRequest, ReceiveMessageRequest, SearchGroupRequest, SearchUserRequest, SendMessageRequest, SetStatusRequest, StopReceiveMessageRequest, User, FindUserRequest, FindGroupRequest } from "../protoCompiled/chat-message_pb";
import { createMessage } from "./MessagesService";
import { ChatServiceClient } from "../protoCompiled/Chat-serviceServiceClientPb";
let Connection : ChatServiceClient | undefined = new ChatServiceClient('http://localhost:8000');

export const startConnection = ()=> {
    if(Connection !== undefined) return;
    console.log("starting connection");
    Connection = new ChatServiceClient('http://localhost:8000');
}

export const DisconnectServer = ()=> Connection = undefined;


export const joinUser = async (user:User) => {
    try{
        console.log(user);
        const resp = await Connection!.join(user);
        console.log(resp);
        return resp;
    }
    catch(error : any){ 
        console.log(error)

        //adding a mechanism to help the higher level know the type of the error and take action
        switch(error.code) {
            case 2 : 
                throw new Error ("Server is not running");
            case 3:
                throw new Error("User already exists");
            default:
                throw new Error("Internal error ! Something went wrong");
        }
    }
}

export const joinUserByName = async (username:string) => {
   const user : User = new User();
   user.setName(username);
   const resp = await Connection!.join(user);
   console.log(resp);
   return resp;
}

export const getAllUsers = async () => {
    const empty = new Empty();
    try{
        const resp =  Connection!.getAllUsers(empty);
        console.log(resp);
        return resp; 
    } catch(error : any){ 
        console.log("fama moshkla ")
        if(error.code === 2) {
            throw new Error ("Server is not running");
        }
        throw new Error("Internal error ! Something went wrong");
    }
    
}

export const getAllGroups = async () => {
    const empty = new Empty();
    const resp =  Connection!.getAllGroups(empty);
    console.log(resp);
    return resp;
}

export  const findUser = async (username:string) => {
    const searchUserRequest : SearchUserRequest = new SearchUserRequest();
    const filter : Filter = new Filter();
    filter.setName(username);
    searchUserRequest.setName(filter);
    const resp = Connection!.searchUser(searchUserRequest);
    console.log(resp);
    return resp;
}


export const findGroup = async (groupname:string) => {
    const searchGroupRequest : SearchGroupRequest = new SearchGroupRequest();
    const filter : Filter = new Filter();
    filter.setName(groupname);
    searchGroupRequest.setName(filter);
    const resp = Connection!.searchGroup(searchGroupRequest);
    console.log(resp);
    return resp;
}


export const connectToChannel = async (user:User, channel:ChannelChat) => {
    const Request : ConnectChannelRequest= new ConnectChannelRequest();
    Request.setUser(user);
    Request.setChannel(channel);
    const resp = await Connection!.connectToChannel(Request); 
    console.log(resp);
    return resp;
}

export const disconnectToChannel = async (user:User, channel:ChannelChat) => {
    const Request : DisconnectChannelRequest= new DisconnectChannelRequest();
    Request.setUser(user);
    Request.setChannel(channel);
    const resp = await Connection!.disconnectToChannel(Request); 
    console.log(resp);
    return resp;
}

export const joinChannel = async (user1:User, user2:User) => {
    const Request : JoinChannelRequest= new JoinChannelRequest();
    Request.setUser1(user1);
    Request.setUser2(user2);
    const resp = await Connection!.joinChannel(Request); 
    console.log(resp);
    return resp;
}

export const sendMessage = async (user:User, channel:ChannelChat, content:string) => {
    const message :ChatMessage = createMessage(user.getId(), content);
    const Request = new SendMessageRequest();
    Request.setSender(user);
    Request.setChannel(channel);
    Request.setMessage(message);
    const resp = await Connection!.sendMsg(Request);
    return resp;

}


export const ReceiveMessage = async (channel:ChannelChat,receiver:User) => {
    const Request = new ReceiveMessageRequest();
    Request.setChannel(channel);
    Request.setReceiver(receiver)
    const msgList = Connection!.receiveMsg(Request); 
    return msgList;
}

export const StopReceiveMessage = async(user : User,channel : ChannelChat) =>{
    const Request = new StopReceiveMessageRequest();
    Request.setReceiver(user);
    Request.setChannel(channel);
    const resp = await Connection!.stopReceiveMsg(Request);
    console.log(resp);
    return resp;
}


export const getConnectedChannels = async (user:User) => {
    const resp = Connection!.getConnectedChannel(user);
    console.log(resp);
    return resp;
}

export const setStatus = async (user : User , status : Status) => {
    const req = new SetStatusRequest();
    req.setStatus(status);
    req.setUser(user);
    const resp = await Connection!.setStatus(req);
    console.log(resp);
    return resp;
}


export const getStatus = async (user : User) => {
    const resp = await Connection!.getStatus(user);
    console.log(resp);
    return resp;
}


export const findUserById = async (id:string) => {
    const req = new FindUserRequest();
    req.setId(id);
    const resp = await Connection!.findUser(req);
    console.log(resp);
    return resp;
}

export const findGroupById = async(id:string) => {
    const req = new FindGroupRequest();
    req.setId(id);
    const resp = await Connection!.findGroup(req);
    console.log(resp);
    return resp;
}