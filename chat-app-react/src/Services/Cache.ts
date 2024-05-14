import { Group, User } from '../protoCompiled/chat-message_pb';
import { findGroupById, findUserById } from './ClientService';
const cacheUsers : Map<String,User>  = new Map<String,User>();
const cacheGroups : Map<String,Group>  = new Map<String,Group>();

export const addUserToCache = (user : User) => { //update and add to cache
        cacheUsers.set(user.getId(),user);
}
export const addGroupToCache = (group : Group) => { //update and add to cache
        cacheGroups.set(group.getId(),group);
}

export async function getUserbyId(userid: string): Promise<User | undefined> { //update and add to cache
    if(!cacheUsers.has(userid)){
        try {
            const user = await findUserById(userid);
            addUserToCache(user);
        } catch (error) {
            console.log(error);
            return undefined;
        }
    }
    else{
        console.log("its already in the cache");
    }
    return Promise.resolve(cacheUsers.get(userid));
}

export const getGroupbyId = async (groupid : string) => { //update and add to cache
    if(!cacheGroups.has(groupid)){
        try {
            const group = await findGroupById(groupid);
            addGroupToCache(group);
            return group;
        } catch (error) {
            console.log(error);
            return undefined;
        }
    }
    return Promise.resolve(cacheGroups.get(groupid));
}

export const getChannelImage = async (channelId : string , userid : string)  : Promise<string | undefined> => {
    if(channelId.includes(userid)){///private 2 users channel
        const Users : string[] = channelId.split(":");//id channel = private:user1:user2
        const otherUser = (Users[1]===userid) ? Users[2] : Users[1];
        const user: User | undefined = await getUserbyId(otherUser);
        return user ? user.getAvatar() : undefined;
    }
    return Promise.resolve(undefined);
}

export const getChannelName = async (channelId : string , userid : string)  : Promise<string | undefined> => {
    if(channelId.includes(userid)){///private 2 users channel
        const Users : string[] = channelId.split(":");//id channel = private:user1:user2
        const otherUser = (Users[1]===userid) ? Users[2] : Users[1];
        const user: User | undefined = await getUserbyId(otherUser);
        return user ? user.getName() : undefined;
    }
    else {
        const group : Group | undefined = await getGroupbyId(channelId);
        return Promise.resolve(group ? group.getName() : undefined); 
    }
}
