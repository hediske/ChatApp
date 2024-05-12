import React, { useEffect , useState } from "react";
import { Status, User } from "../protoCompiled/chat-message_pb";
import Sidebar from './Sidebar';
import UserList from "./UserList";
import { getAllUsers } from "../Services/ClientService";

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

    useEffect(() => {
        setMessage("")

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
                    resp.on("data", (data) => {
                        handleLoading(false);
                        const user = new User();
                        user.setId(data.getId());
                        user.setName(data.getName());
                        user.setAvatar(data.getAvatar());
                        user.setStatus(data.getStatus());
                        users.push(user);


                    });      
                    setUserList(users);
                })
            


        }
    },[main]);

    const handleSideBar = (str : string) => {
        setMain(str);
    }

    return (            
        <div style={style.root}>
            <div style={style.Container}>
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
                        <UserList size={"30%"} userList={[user]} Message={"No User is connected"} ></UserList>
                        : main === 'group' ? 
                            <div>Group</div> 
                                : <UserList size={"100%"} userList={userlist} Message={message}></UserList>}
                </div>
            </div>

        </div>
    );
}


export default Chat