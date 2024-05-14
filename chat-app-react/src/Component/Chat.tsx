import React, { useEffect , useState } from "react";
import { ChannelChat, ChatMessage, Status, User } from "../protoCompiled/chat-message_pb";
import Sidebar from './Sidebar';
import UserList from "./UserList";
import { ReceiveMessage, StopReceiveMessage, getAllUsers, getConnectedChannels, joinChannel, sendMessage } from "../Services/ClientService";
import ChannelList from "./ChannelList";
import CustomModal from './CustomModal';
import { Button, TextField, makeStyles } from "@material-ui/core";
import { ClientReadableStream } from "grpc-web";
import MessageList from "./MessageList";


const useStyles = makeStyles({
    customInputBase: {
      height :"100%",
      
    },
  });

  
const style : { [key: string]: React.CSSProperties } =  {
    Container : {
        display : "flex",
        flexDirection : "row",
        backgroundColor: "#BDBDBD",
        height :"80vh",
        width : "90vw",
        borderRadius : "10px",
        maxWidth : "90vw",
        minWidth:"500px",
        
    },
    root: {
        display : "flex",
        justifyContent : "center",
        alignItems : "center",
        flexDirection : "column",
        height:"100vh",
    },
    MainContainer : {
        display : "flex",
        flexDirection:"column",
        width : "100%",
        padding : "10px",
        gap : "30px",
    },
    MainHeader :{
        display : "flex",
        flexDirection : "row",
        height : "10%",
        backgroundColor: "#3e3e42",
        justifyContent : "flex-end",
        borderRadius : "10px",

    },

    InfoBar :{
        display : "flex",
        flexDirection : "row",
        alignItems : "center",
        justifyContent : "space-around",
        width : "30%",
        fontSize : "1.5vw",
        color : "aliceblue",

    },
    ChatContainer : {
        width : "100%",
        height: "100%",
        display : "flex",
        flexDirection:"row"

    },
    MessagingContainer :{
        width : "100%",
        height: "100%",
        display : "flex",
        flexDirection:"column",
        borderRadius : "10px",
        background :  "grey",
        marginLeft : '15px',
        paddingLeft : '15px',
        paddingRight : '15px',
        gap:"10px"
    }
}
interface CustomProps {
     handleTitle : React.Dispatch<React.SetStateAction<string>>;
     handleDescription :React.Dispatch<React.SetStateAction<string>>;
     onPopup :() => void;
     onClose : () => void;
     user : User;
     handleLoading : React.Dispatch<React.SetStateAction<boolean>> ;

}
const messageList : Map<string,ChatMessage[]> = new Map();
const Chat : React.FC<CustomProps> = (props) => {

    const {handleTitle, handleDescription, onPopup, onClose, user, handleLoading} = props;
    const [main,setMain] = useState<string>('chat');
    const [message,setMessage] = useState<string>('');
    const [userlist , setUserList] = useState<User[]>([]);
    const [channelList , setChannelList] = useState<ChannelChat[]>([]);
    const [SelectedUser, changeUser ] = useState<User>();
    const [SelectedChannel, changeChannel ] = useState<ChannelChat | undefined>(undefined);
    const [SelectedAvatar, changeAvatar ] = useState<string | undefined>(undefined);
    const [SelectedName, changeName ] = useState<String | undefined>('');
    const [SelectedChannelStream, changeChannelStream ] = useState<Promise<ClientReadableStream<ChatMessage>> | undefined>(undefined);
    const [showJoinDialog, setShowJoinDialog] = useState(false);
    const [textFieldValue, setTextFieldValue] = useState('');
    const [messageListValue, setMessageListValue] = useState<ChatMessage[] | undefined>(undefined);
    const [latestMessage, setLatestMessage] = useState<number>();
    const classes = useStyles();

    const onSelectUser =  (selected : User) => {
        if(user.getId() !== selected.getId()) {
            changeUser(selected);
            setShowJoinDialog(true);
        }
    }

    const onSelectChannel =  (selected : ChannelChat , name : Map<string,string>, avatar : Map<string,string>) => {
            // if(selected !== undefined)     StopReceiveMessage(user ,selected);
            changeChannel(selected);
            changeName(name.get(selected.getIdChannel()));
            changeAvatar(avatar.get(selected.getIdChannel()));
            const  resp = ReceiveMessage(selected,user);
            changeChannelStream(resp);

    }



    const handleJoinChannel = () => {
        const resp : Promise<ChannelChat> = joinChannel(user, SelectedUser!);
        resp.then((data : ChannelChat) => {
            console.log(data);
            handleTitle("Successfully joined channel !");
            handleDescription("You are now connected to "+SelectedUser!.getName());
            onPopup();
        })
        .catch((error) => {
            console.log(error);
            handleTitle("You Already joined the channel !");
            handleDescription("You are already connected to User "+SelectedUser!.getName());
            onPopup();

        })
        setShowJoinDialog(false);
      };
    
      const handleCloseDialog = () => {
        setShowJoinDialog(false);
      };

      // Here we are using the useEffect hook to listen to the SelectedChannelStream when it changes.
      // The SelectedChannelStream is a Promise that resolves to a ClientReadableStream<ChatMessage>.
      // The ClientReadableStream<ChatMessage> is a stream that emits data events whenever a new message is received.
      // So, we can use the then method to get the actual stream and then listen to the data events.
      // The data event contains the message object that contains the sender and the message.
      // We can log each message received and listen to every message.
      useEffect(()=>{
        SelectedChannelStream?.then((stream : ClientReadableStream<ChatMessage>) => {
          console.log("started listening to channel");
          stream.on("error", (error) => {
              console.log('errrorrrr : '+error);
              handleTitle("ERROR FETCHING MESSAGES");
              handleDescription("Internal error : Something went wrong");
              onPopup();
          })
          stream.on("data", (data) => {
            console.log("sender: " + data.getSender() + " message: " + data.getMsg());
            console.log("taille inititale : "+ messageList.size);
            const key = SelectedChannel!.getIdChannel();
            if(messageList.has(key)) {
                messageList.get(key)?.push(data);
            }else {
                messageList.set(SelectedChannel!.getIdChannel(), [data]);
            }
            console.log("taille après ajout du clonage : "+ messageList.size);
            setMessageListValue(messageList.get(key));
            setLatestMessage(data.getTime()?.getSeconds());
          });
        });
      },[SelectedChannelStream]);



    useEffect(() => {
        setMessage("")

        if(main=='chat'){
            if(SelectedChannel !== undefined) {
                //TODO:
            }
            handleLoading(true);
            getConnectedChannels(user).then((resp) => {
                const channels : ChannelChat[] = [];
                resp.on("error",(error) => {
                    handleTitle("ERROR FETCHING CHANNELS");
                    handleDescription("Internal error : Something went wrong")
                    onPopup();
                    setMessage("No Channel was Found")
                    handleLoading(false);
                });
                resp.on("end", () => {
                    setChannelList(channels);
                    handleLoading(false);

                })
                resp.on("data", (data) => {
                    const channel = new ChannelChat();
                    channel.setIdChannel(data.getIdChannel());
                    channel.setStatusChannel(data.getStatusChannel());
                    channel.setTypeChannel(data.getTypeChannel());
                    channels.push(channel);
                });      
            })
            if(channelList.length === 0) 
                setMessage("You are not connected to any channel ! Go and join one !")


        }

        if(main === 'users') {
            handleLoading(true);
                getAllUsers().then((resp) => {
                    const users : User[] = [];
                    resp.on("error",(error) => {
                        handleTitle("ERROR FETCHING USERS");
                        handleDescription("Internal error : Something went wrong")
                        onPopup();
                        setMessage("No User was Found")
                        handleLoading(false);
                    });
                    resp.on("end", () => {
                        setUserList(users);
                        handleLoading(false);

                    })
                    resp.on("data", (data) => {
                        const user = new User();
                        user.setId(data.getId());
                        user.setName(data.getName());
                        user.setAvatar(data.getAvatar());
                        user.setStatus(data.getStatus());
                        users.push(user);
                    });      
                })
            


        }
    },[main]);

    const handleSideBar = (str : string) => {
        setMain(str);
    }

    const handleTextFieldChange = (event : React.ChangeEvent<HTMLInputElement>) => {
        setTextFieldValue(event.target.value);
    }

    const handleSendMessage = () => {
        if(SelectedChannel !== undefined && textFieldValue !== '') {
            try{
            sendMessage(user, SelectedChannel, textFieldValue).catch(
                error =>    {
                    console.log(error);
                    handleTitle("ERROR SENDING MESSAGE");
                    handleDescription(error.message);
                    onPopup();
                }
            );}
            catch(error){
                console.log("a problem")
            }
            setTextFieldValue('');            
        }
    }

    return (            
        <div style={style.root}>
            <div style={style.Container}>
                <CustomModal open={showJoinDialog} handleClose={handleCloseDialog} handleConfirm={handleJoinChannel}>
                <>
                    <div className='modal-component' id="transition-modal-title">
                        Do you want to join this channel with {SelectedUser?.getName()} ?
                    </div>
                </>
                </CustomModal>  
                <Sidebar handleSidebar= {handleSideBar}></Sidebar>
                <div style={style.MainContainer}> 
                    <div style={style.MainHeader}>
                        <div style={style.InfoBar}>
                        <span> {user.getName()}</span>
                        <span className={`${user.getStatus() === Status.ONLINE  ? 'connected' : (user.getStatus() === Status.OFFLINE ? 'disconnected' : 'absent')}`}>
                        </span>
                        <img src={user.getAvatar()} style={{width : "80px", height : "80px", borderRadius : "50%"}}/>
                        </div>
                    </div>
                    {main === 'chat' ? 
                        <div style={style.ChatContainer}>
                            <ChannelList onSelectChannel={onSelectChannel}  size={channelList.length === 0 ? "100%" :"40%"} channelList={channelList} userid={user.getId()} Message={message} ></ChannelList>
                            {SelectedChannel!==undefined && 
                                    <div style={style.MessagingContainer}> 
                                        <div style={{width : "inherits" , paddingLeft : "6%", height:"10%" , background :" #121212" , borderRadius:"10px"  , display:"flex" ,  alignItems:"center" , color: "aqua" , fontSize:'3rem' , fontWeight:"bold"}}>
                                            {SelectedAvatar ? <img style={{ marginRight:"20%",height:"80px" , width : "80px" , borderRadius:"50% "}} src={SelectedAvatar}></img> : <></> }
                                            <span style = {{fontSize : "2rem"}}>  CHANNEL  </span>
                                            {"  : " +SelectedName}
                                        </div>
                                        <div style = {{width: "100%" , flex : "1" , background:"#F0F8FF", borderRadius:"10px"}}>
                                            {/* {messageList.get(SelectedChannel.getIdChannel())?.map((message : ChatMessage) => (
                                                   <> {message.getMsg()} </>
                                            ))}                                             */}
                                        <MessageList key={latestMessage} messageList={messageListValue} user ={user}></MessageList>

                                        </div>
                                        <div style = {{width: "100%" , color:"aliceblue", height:"10%" , background:"#06062c", borderRadius:"10px" , display:"flex" ,flexDirection :"row"}}>
                                            <TextField classes={{ root: classes.customInputBase }}
                                            value={textFieldValue}
                                            onChange={handleTextFieldChange}

                                            fullWidth
                                            InputLabelProps={{
                                                style: {
                                                  color: "aliceblue",
                                                  fontFamily : "Poppins",
                                                },
                                              }}
                                              InputProps={{
                                                style: {
                                                  color: "aliceblue",
                                                  height:"100%",
                                                  fontFamily : "Poppins",
                                                  fontSize : "2vh",
                                                  paddingLeft:"10px"
                                  
                                                },
                                              }}
                                  
                                            />
                                            <Button onClick={handleSendMessage} variant="contained" color="primary">Send</Button>
                                        </div>
                                    </div>
                            }
                        </div>
                        : main === 'group' ? 
                            <div style={{fontSize:"2rem"}}>"Service not available ! to add in V2"</div> 
                                : <UserList onSelectUser={onSelectUser}  size={"100%"} userList={userlist} Message={message}></UserList>}
                </div>
            </div>

        </div>
    );
}


export default Chat