import { Slide, Slider } from "@material-ui/core"
import { ChatMessage, User } from "../protoCompiled/chat-message_pb"
import { getUserbyId } from "../Services/Cache"
import useEffect, { useState } from 'react';



interface customProps {
    messageList : ChatMessage[] | undefined,
    user : User
    

}
const MessageList : React.FC<customProps> = (props) => {
    const {messageList , user} = props
    const [userSender , setUser] = useState<string>("");
    const [name , setName] = useState<string>("");

    return(
        <div style ={{width:"100%" , height:"100%" , display:"flex" , flexDirection:"column" , overflow:"auto" }}>
            {messageList?.map((message : ChatMessage) => (
                <div className={user.getId() === message.getSender() ? "mineMsg" : "notmineMsg"} style={{margin:"10px" , width:"40%"}}>
                <div>
                    {/* {getName(message.getSender())} */}
                    <span>"  |  "</span>
                    {new Date(message.getTime()!.getSeconds() * 1000).toLocaleString()}</div>
                     <div style ={{display:"flex" , flexDirection:"row" , height:"70px" ,width:"100%" }}>
                    <div className={user.getId() === message.getSender() ? "mine" : "notmine"} style={{display:"flex" , alignItems:'center ' , justifyContent:"flex-start" , paddingLeft:"10%" , fontSize:"1.5rem" , flexDirection:"row" , height:"70px" ,width:"100%"  , borderRadius:"10px" }}>
                    {message.getMsg()}
                    </div>

                 </div>
                </div>
                                            ))}

        </div>

    )
}


const getName = async (userid: string): Promise<string> => {
    try {
        const user = await getUserbyId(userid);
        return user ? user.getName() : "";
    } catch (error) {
        console.error("Error fetching user by id:", error);
        return "";
    }
}

export default MessageList