import React from 'react';
import { makeStyles } from '@material-ui/core';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMessage, faUserGroup, faUsers } from '@fortawesome/free-solid-svg-icons';
interface propSideBar {
    handleSidebar : (arg0: string) => void;
}
const style : { [key: string]: React.CSSProperties } = {
    container : {
        display : "flex",
        justifyContent : "center",
        alignItems : "center",
        flexDirection : "column",
        height:"100%  ",
        width : "15%",
        backgroundColor: "#2E2A2E",
        borderRadius : "10px",



    },
    sidebarContent : {
        display : "flex",
        justifyContent : "center",
        alignItems : "center",
        flexDirection : "column",
        height :"50%",
        width : "95%",
        whiteSpace:"wrap",
        gap:"10px",
        

        

    },

    option : {
      cursor:'pointer',
      flex:'1'
    }
}
const Sidebar : React.FC<propSideBar> = (props) => {
  const {handleSidebar} = props;
  const [type, setType] = React.useState('chat');

  const handleClick = (type: string) => {
    handleSidebar(type);
    setType(type);
  } 
  
  return (
    <div style={style.container}>
      <div style={style.sidebarContent}>
        <div className={`sidebar ${type === 'chat' ? 'selected' : ''}`} onClick={() => handleClick('chat')} style={style.option}>
          <span className="label-option">Messages</span>
          <FontAwesomeIcon className="icon" icon={faMessage} ></FontAwesomeIcon>
        </div>
        <div className={`sidebar ${type === 'group' ? 'selected' : ''}`} onClick={() => handleClick('group')} style={style.option}>
        <span className="label-option">Groups</span>
          <FontAwesomeIcon  className="icon" icon={faUserGroup} ></FontAwesomeIcon>
        </div>
        <div className={`sidebar ${type === 'users' ? 'selected' : ''}`} onClick={() => handleClick('users')} style={style.option}>
        <span className="label-option">Users</span>
          <FontAwesomeIcon className="icon" icon={faUsers} ></FontAwesomeIcon>

        </div>
      </div>
      </div>
  );
};

export default Sidebar;