import { randomUUID } from "crypto";
import { ChatMessage } from "../protoCompiled/chat-message_pb"


export const createMessage = (sender : string,  content : string)=>{
    const message : ChatMessage = new ChatMessage();
    message.setSender(sender);
    message.setMessageid(randomUUID().toString());
    message.setMsg(content);
    message.setTime(Date.now());
    return message ;
}


