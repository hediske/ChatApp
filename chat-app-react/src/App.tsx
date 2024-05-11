  import React ,{ useEffect, useState } from 'react';
  import './App.css';
  import Greeting from './Component/Greeting';
  import Chat from './Component/Chat';
  import { joinUser, startConnection } from './Services/ClientService';
  import { JoinResponse, User } from './protoCompiled/chat-message_pb';
  import { Status } from './protoCompiled/chatPackage/Status';

startConnection();
function App(){
  

  const [user,userHandler] = useState<User>();
  const [popup,setPopup] = useState<boolean>(false);
  const [loading ,setLoading] = useState<boolean>(false);
  useEffect(()=>{
    startConnection();
  },[]);

  const handleUsernameAdding = ( name: string, avatar: string) => {
    console.log('name :>> ', name);
    console.log('avatar :>> ', avatar);
    if(!name ) return ;
    const newUser = new User();
    newUser.setName(name);
    newUser.setAvatar(avatar);
    newUser.setStatus(Status.ONLINE);
    try {
      const resp: Promise<JoinResponse> = joinUser(newUser);
      resp.then((res) => console.log(res))
          .catch((error) => console.log(error)); // Add a catch block to handle errors
    } catch (error:any) {
      console.log(error.message);
    }
  }

    return (
      
      <div className='App'>
        <div className='App-container'>
          {!user ? <Greeting onUsernameEnter={handleUsernameAdding}/> : <Chat/>};
          {/* {popup ? <Alert open={popup}></Alert>}; */}
        </div>
      </div>
    );
}
  export default App

  
  
  
  
  