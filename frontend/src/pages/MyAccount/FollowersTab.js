import React from 'react';
import FollowerCard from "components/FollowerCard";
import { InboxOutlined } from "@ant-design/icons";
import { getUser } from "utilities/localStorage";

const FollowersTab = () => {

    const followers = getUser().follows.map(follower => follower.follower);

    return (
        <div className='second-tab'>
            { followers?.length ?
                followers.map(({ username, email }) => <FollowerCard key={username} username={ username } email={ email }/>)
                :
                <div className='no-content'>
                    <InboxOutlined/>
                    <h2>You have no followers yet!</h2>
                </div>
            }
        </div>
    )
}

export default FollowersTab;