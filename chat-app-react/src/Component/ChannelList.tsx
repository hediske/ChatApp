import React from 'react';
import { ChannelChat, Status, User } from '../protoCompiled/chat-message_pb';
import { getAllUsers } from '../Services/ClientService';

interface ChannelListProps {
    channelList: ChannelChat[];
    size : string;
    Message :string;
    onSelectChannel : (channel : ChannelChat) => void;
}

const ChannelList: React.FC<ChannelListProps> = (props) => {
    const { channelList, size ,Message, onSelectChannel } = props;
    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', width: size , borderRadius : '10px' ,border:"1px solid black" ,height : '100%' , backgroundColor : '#D3D3D3'}}>
            {(channelList.length === 0) ?
                <h1 style={{marginTop : '30px'}}>{Message}</h1> : null
            }
            {channelList.map((channel: ChannelChat) => (
                <div onClick={() => onSelectChannel(channel)} key={channel.getIdChannel()} style={{ display: 'flex', alignItems: 'center',justifyContent: 'space-around', width: '100%' , cursor : 'pointer' ,marginTop:"10px", backgroundColor : '#FAFAFA' , borderRadius : '10px' }}>
                    <span style={{ marginRight: '10px' , fontWeight: 'bold' , fontSize: '2rem' }}>{channel.getIdChannel()}</span>
                    <span style={{ marginRight: '10px' , fontSize: '2rem' }}>{channel.getTypeChannel()}</span>
                </div>
            ))}
        </div>
    );
};

export default ChannelList;
