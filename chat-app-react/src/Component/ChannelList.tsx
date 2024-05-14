import React from 'react';
import { ChannelChat, Status, User } from '../protoCompiled/chat-message_pb';
import { findUserById, getAllUsers } from '../Services/ClientService';
import { getChannelImage, getChannelName } from '../Services/Cache';

interface ChannelListProps {
    channelList: ChannelChat[];
    size : string;
    Message :string;
    onSelectChannel : (channel : ChannelChat , name:Map<string,string> , avatar : Map<string,string> ) => void;
    userid : string;
    
}

let id : string ;
let avatarList : Map<string,string> = new Map<string,string>();
let nameList : Map<string,string> = new Map<string,string>();
const ChannelList: React.FC<ChannelListProps> = (props) => {
    const { channelList, size ,Message, onSelectChannel,userid} = props;
    // const [name, setName] = React.useState<Map<string,string>>(new Map<string,string>());
    // const [avatar, setAvatar] = React.useState<Map<string,string> >(new Map<string,string>());


    return (
        <div style={{ overflow:"auto" , display: 'flex', flexDirection: 'column', alignItems: 'center', width: size , borderRadius : '10px' ,border:"1px solid black" ,height : '100%' , backgroundColor : '#D3D3D3'}}>
            {(channelList.length === 0) ?
                <h1 style={{marginTop : '30px'}}>{Message}</h1> : null
            }
            {channelList.map((channel: ChannelChat ) => {
                //getNameforthechannel
                getChannelName(channel.getIdChannel(),userid).then(value => {
                    if (value !== undefined) {
                        nameList.set(channel.getIdChannel(), value);
                        // setName(nameList);
                    }
                    else {
                        nameList.set(channel.getIdChannel(), "");
                        // setName(nameList);
                    }
                });
               //TODO: id = name ? name : ""
                getChannelImage(channel.getIdChannel(),userid).then(value => {
                    if(value !==undefined){
                        avatarList.set(channel.getIdChannel(), value);
                        // setAvatar(avatarList);
                    }
                    else {
                        avatarList.set(channel.getIdChannel(), "");
                        // setAvatar(avatarList);

                    }
                });
                return (
                    <div onClick={() => onSelectChannel(channel,nameList,avatarList)} key={channel.getIdChannel()} style={{ display: 'flex', alignItems: 'center',justifyContent: 'space-around', width: '100%' , cursor : 'pointer' ,marginTop:"10px", backgroundColor : '#FAFAFA' , borderRadius : '10px' }}>
                        <span style={{ marginRight: '10px' , fontWeight: 'bold' , fontSize: '3rem' }}><span style={{ fontSize: '1.5rem' }} >CHANNEL : </span>{nameList.get(channel.getIdChannel())}</span>
                        <span style={{ marginRight: '10px' , fontSize: '2rem' }}>{channel.getTypeChannel()===1 ? "Public" : "Private"}</span>
                    </div>
                )
            })}
        </div>
    );
};



export default ChannelList;
