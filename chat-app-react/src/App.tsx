  import React ,{ Children, useEffect, useState } from 'react';
  import './App.css';
  import Greeting from './Component/Greeting';
  import Chat from './Component/Chat';
  import { joinUser, startConnection } from './Services/ClientService';
  import { JoinResponse, User } from './protoCompiled/chat-message_pb';
  import { Status } from './protoCompiled/chatPackage/Status';
  import LoadingScreen from './Component/LoadingScreen';
import CustomModal from './Component/CustomModal';

function App(){
  

  const [title,setTitle] = useState<string>('');
  const [description,setDescription] = useState<string>('');
  const [user,userHandler] = useState<User>();
  const [popup,setPopup] = useState<boolean>(false);
  const [loading ,setLoading] = useState<boolean>(false);
  useEffect(()=>{
    if(!user) return ;
    
  },[user]);
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
    if(name === ""|| avatar==="") {
      setTitle("ERROR ADDING THE USER");
      setDescription("Please provide a username and an avatar to continue !");
      setPopup(true);
      return ;
    }
    const newUser = new User();
    newUser.setName(name);
    newUser.setAvatar(avatar);
    newUser.setStatus(Status.ONLINE);
    setLoading(true);
    try {
      const resp: Promise<JoinResponse> = joinUser(newUser);

      resp.then((res : JoinResponse) => {
        setLoading(false);
        userHandler(newUser.setId(res.getId()));
      })
       .catch((error) => {
            setTitle("ERROR ADDING THE USER");
            setDescription(error.message);
            setLoading(false);
            setPopup(true);
          }); 
    } catch (error:any) {
      console.log(error.message);
    }
  }

  return (
    <div>
      <div>
        <CustomModal open={popup} handleClose={handleClose}>
          <>
            <div className='modal-component'  id="transition-modal-title">
                {title}
            </div>
            <p className='modal-component' id="transition-modal-description">
                {description}
            </p>
          </>
        </CustomModal>
      </div>

      <div>
        {loading ? (
          <LoadingScreen />
        ) : null}
        
        {!user ? (
              <Greeting onUsernameEnter={handleUsernameAdding} />
            ) : (
              <Chat 
                handleTitle={setTitle}
                handleDescription={setDescription} 
                onPopup={handleOpen} onClose={handleClose} 
                user={user} handleLoading ={setLoading} 
                />
        )}
          
        
      </div>
    </div>
  );
};
  export default App

  
  
  
  
  