import React, { useState } from 'react'
import { Row, Col, Radio } from "antd";
import { Avatar } from "antd";
import { CalendarOutlined, HomeOutlined, MailOutlined, UserOutlined } from "@ant-design/icons";
import { getUser } from "utilities/localStorage";
import moment from "moment";
import EditTab from "pages/MyAccount/editTab";
import 'pages/MyAccount/myAccount.scss'
import FollowersTab from "pages/MyAccount/FollowersTab";
import FavoritesTab from "pages/MyAccount/FavoritesTab";

const MyAccount = () => {

    const user = getUser();
    const [counter, setCounter] = useState(0);
    const tabs = [<EditTab/>, <FollowersTab/>, <FavoritesTab/>]

    const radioButtonHandler = (event) => {
        setCounter(event.target.value)
    }

    return (
        <Row className="my-account-page">
            <Col span={10}>
                <Avatar size={200} icon={<UserOutlined/>}/>
                <Radio.Group buttonStyle={'solid'} defaultValue={0} onChange={radioButtonHandler}
                             className='my-account-radio-group'>
                    <Radio.Button value={0}>Edit</Radio.Button>
                    <Radio.Button value={1}>Followers</Radio.Button>
                    <Radio.Button value={2}>Favorites</Radio.Button>
                </Radio.Group>

                <div align={'left'} className='my-account-radio-group'>
                    <h3><UserOutlined/> {`${user.firstName} ${user.lastName}`}</h3>
                    <h3><UserOutlined/> {user.username}</h3>
                    <h3><MailOutlined/> {user.email}</h3>
                    {user?.city && <h3><HomeOutlined/> {user?.city}</h3>}
                    <h3><CalendarOutlined/> account created
                        at {moment(user.dateCreated).local().format("D MMMM YYYY [at] HH:mm")}</h3>
                </div>
            </Col>
            <Col span={13} offset={1}>
                {tabs[counter]}
            </Col>
        </Row>
    )
}

export default MyAccount;
