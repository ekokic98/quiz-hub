import React from 'react';
import { Card, Avatar } from "antd";
import { UserOutlined } from "@ant-design/icons";

const {Meta} = Card;

const FollowerCard = ({username, email}) => {
    return (
        <Card style={{width: 300, marginTop: 16, height: 100}}>
            <Meta
                avatar={<Avatar size={50} icon={<UserOutlined/>}/>}
                title={username}
                description={email}
            />
        </Card>
    );
}

export default FollowerCard;
