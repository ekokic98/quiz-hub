import React, { useState } from 'react'
import { Row, Col, Radio } from "antd";
import Avatar from "antd/es/avatar/avatar";
import { CalendarOutlined, HomeOutlined, MailOutlined, UserOutlined } from "@ant-design/icons";
import { getUser } from "utilities/localStorage";
import moment from "moment";
import EditTab from "pages/MyAccount/editTab";

const MyAccount = () => {

    const user = getUser();
    const [counter, setCounter] = useState(0);
    const tabs = [<div>0</div>, <EditTab/>, <div>2</div>, <div>3</div>]

    const radioButtonHandler = (event) => {
        setCounter(event.target.value)
    }

    return (
        <Row>
            <Col span={ 10 }>
                <Avatar size={ 200 } icon={ <UserOutlined/> }/>
                <Radio.Group defaultValue={ 0 } buttonStyle="solid" onChange={ radioButtonHandler }>
                    <Radio.Button value={ 0 }>Summary</Radio.Button>
                    <Radio.Button value={ 1 }>Edit</Radio.Button>
                    <Radio.Button value={ 2 }>Followers</Radio.Button>
                    <Radio.Button value={ 3 }>Favorites</Radio.Button>
                </Radio.Group>

                <div align={ 'left' }>
                    <h3><UserOutlined/> { `${ user.firstName } ${ user.lastName }` }</h3>
                    <h3><UserOutlined/> { user.username }</h3>
                    <h3><MailOutlined/> { user.email }</h3>
                    <h3><HomeOutlined/> { `${ user?.city }, ${ user?.country }` }</h3>
                    <h3><CalendarOutlined/> account created at { moment(user.dateCreated).local().format("D MMMM YYYY [at] HH:mm") }</h3>
                </div>
            </Col>
            <Col span={ 14 }>
                { tabs[counter] }
            </Col>
        </Row>
    )
}

export default MyAccount;
