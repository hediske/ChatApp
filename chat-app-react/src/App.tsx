  import React ,{ Children, useEffect, useState } from 'react';
  import './App.css';
  import Greeting from './Component/Greeting';
  import Chat from './Component/Chat';
  import { joinUser, startConnection } from './Services/ClientService';
  import { JoinResponse, User } from './protoCompiled/chat-message_pb';
  import { Status } from './protoCompiled/chatPackage/Status';
  import LoadingScreen from './Component/LoadingScreen';
import CustomModal from './Component/CustomModal';

startConnection();
function App(){
  

  const [title,setTitle] = useState<string>('');
  const [description,setDescription] = useState<string>('');
  const [user,userHandler] = useState<User>();
  const [popup,setPopup] = useState<boolean>(false);
  const [loading ,setLoading] = useState<boolean>(false);
  useEffect(()=>{
    startConnection();
  },[]);

  const handleClose : () => void = () => {
    setPopup(false);
  }

  const handleOpen : () => void  = () => {
    setPopup(true);
  }
  const handleUsernameAdding = ( name: string, avatar: string) => {
    console.log('name :>> ', name);
    console.log('avatar :>> ', avatar);
    if(!name ) return ;
    const newUser = new User();
    newUser.setName(name);
    newUser.setAvatar(avatar);
    newUser.setStatus(Status.ONLINE);
    setLoading(true);
    try {
      const resp: Promise<JoinResponse> = joinUser(newUser);

      resp.then((res) => {
        console.log(res);
        setLoading(false);
        userHandler(newUser);
      })
          .catch((error) => {
            console.log(error);
            setTitle("ERROR ADDING THE USER");
            setDescription(error.message);
            setLoading(false);
            setPopup(true);
            
          }); // Add a catch block to handle errors
      
    } catch (error:any) {
      console.log(error.message);
    }
  }

  return (
    <div>
      <div>
        <CustomModal open={popup} handleClose={handleClose}>
          <>
            <title id="transition-modal-title">
                {title}
            </title>
            <p id="transition-modal-description">
                {description}
            </p>
          </>
        </CustomModal>
      </div>

      <div>
        {loading ? (
          <LoadingScreen />
        ) : (
          <>
            {!user ? (
              <Greeting onUsernameEnter={handleUsernameAdding} />
            ) : (
              <Chat />
            )}
          </>
        )}
      </div>
    </div>
  );
};
  export default App

  
  
  
  
  