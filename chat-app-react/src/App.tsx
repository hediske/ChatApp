  import React ,{ useEffect, useState } from 'react';
  import logo from './logo.svg';
  import './App.css';
  import { ChatServiceClient } from './proto/proto/Chat-serviceServiceClientPb';
  import { User } from './proto/proto/chat-message_pb';


function App(){
useEffect(() => {
      (async() => {
        const client = new ChatServiceClient('http://localhost:8080');
        const user :User = new User();
        user.setName("moham");
        user.setId("1");    
        client.join(user);
        const resp = await client.join(user);
        console.log(resp);
     })();

    },[]);
    return (
      <div className="App">
        <header className="App-header">
          hello world
        </header>
      </div>
    );
  }

  export default App;
