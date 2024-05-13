import React, { useEffect , useState } from "react";
import { ChannelChat, Status, User } from "../protoCompiled/chat-message_pb";
import Sidebar from './Sidebar';
import UserList from "./UserList";
import { getAllUsers, getConnectedChannels, joinChannel } from "../Services/ClientService";
import ChannelList from "./ChannelList";
import CustomModal from './CustomModal';

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
const Chat : React.FC<CustomProps> = (props) => {

    const {handleTitle, handleDescription, onPopup, onClose, user, handleLoading} = props;
    const [main,setMain] = useState<string>('chat');
    const [message,setMessage] = useState<string>('');
    const [userlist , setUserList] = useState<User[]>([]);
    const [channelList , setChannelList] = useState<ChannelChat[]>([]);
    const [SelectedUser, changeUser ] = useState<User>();
    const [SelectedChannel, changeChannel ] = useState<ChannelChat | undefined>(undefined);
    const [showJoinDialog, setShowJoinDialog] = useState(false);



    const onSelectUser =  (selected : User) => {
        if(user.getId() !== selected.getId()) {
            changeUser(selected);
            setShowJoinDialog(true);
        }
    }

    const onSelectChannel =  (selected : ChannelChat) => {
            changeChannel(selected);
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


    useEffect(() => {
        setMessage("")

        if(main=='chat'){
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
                            <ChannelList onSelectChannel={onSelectChannel}  size={channelList.length === 0 ? "100%" :"40%"} channelList={channelList} Message={message} ></ChannelList>
                            {SelectedChannel!==undefined && 
                                    <div style={{width : "100%"}}> 
                                    {SelectedChannel.getIdChannel()}
                                    </div>
                            }
                        </div>
                        : main === 'group' ? 
                            <div>Group</div> 
                                : <UserList onSelectUser={onSelectUser}  size={"100%"} userList={userlist} Message={message}></UserList>}
                </div>
            </div>

        </div>
    );
}


export default Chat