import React from 'react';
import { Status, User } from '../protoCompiled/chat-message_pb';

interface UserListProps {
    userList: User[];
    size : string;
    Message :string;
    onSelectUser : (user : User) => void
}

const UserList: React.FC<UserListProps> = (props) => {
    const { userList, size ,Message , onSelectUser } = props;
    return (
        <div style={{ display: 'flex', overflow:"auto" , flexDirection: 'column', alignItems: 'center', width: size , borderRadius : '10px' ,border:"1px solid black" ,height : '100%' , backgroundColor : '#D3D3D3'}}>
            {(userList.length === 0) ? 
                <h1 style={{marginTop : '30px'}}>{Message}</h1> : null
            }
            {userList.map((user: User) => (
                <div key={user.getId()} style={{ display: 'flex', alignItems: 'center',justifyContent: 'space-around', width: '100%' , cursor : 'pointer' ,marginTop:"10px", backgroundColor : '#FAFAFA' , borderRadius : '10px' }} onClick={() => onSelectUser(user)}>
                    <span style={{ marginRight: '10px' , fontWeight: 'bold' , fontSize: '2rem' }}>{user.getName()}</span>
                    <span
                        className={`${
                            user.getStatus() === Status.ONLINE ? 'connected' : user.getStatus() === Status.OFFLINE ? 'disconnected' : 'absent'
                        }`}
                    ></span>
                    <img src={user.getAvatar()} style={{ width: '80px', height: '80px', borderRadius: '50%' }} />
                </div>
            ))}
        </div>
    );
};

export default UserList;
