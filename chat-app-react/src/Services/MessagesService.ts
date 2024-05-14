import { ChatMessage } from "../protoCompiled/chat-message_pb"
import { v4 } from 'uuid';
import * as prototime from 'google-protobuf/google/protobuf/timestamp_pb';

export const createMessage = (sender : string,  content : string)=>{
    const message : ChatMessage = new ChatMessage();
    message.setSender(sender);
    message.setMessageid(v4());
    message.setMsg(content);
    const currentDate = Date.now();
    const seconds = Math.floor(currentDate / 1000);
    const time : prototime.Timestamp = new prototime.Timestamp();
    time.setNanos(0);
    time.setSeconds(seconds);
    message.setTime(time);
    console.log(message);
    return message ;
}


