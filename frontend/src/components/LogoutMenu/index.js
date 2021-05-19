import React from 'react';
import { myAccountUrl } from "utilities/appUrls";
import Logo from 'assets/images/Logo.svg';
import { Button, Col, Row } from "antd";
import { getUser, removeSession } from "utilities/localStorage";

import "./logoutMenu.scss";
import { useUserContext } from "AppContext";

const LogoutMenu = ({ history }) => {
    const user = getUser();
    const { setLoggedIn } = useUserContext();

    const handleLogout = () => {
        setLoggedIn(false);
        removeSession();
    };

    return (
        <div className='logout-menu'>
            <Row align='middle' justify='space-around'>
                <img src={Logo} alt="Logo" height={50}/>
                <div style={{ marginBottom: 10 }}>Welcome, {user.firstName + " " + user.lastName}</div>
            </Row>
            <Row>
                <Col span={16}>
                    <Button onClick={() => history.push(myAccountUrl)}>My account</Button>
                </Col>
                <Col span={8}>
                    <Button onClick={handleLogout}>Logout</Button>
                </Col>
            </Row>
        </div>
    );
}

export default LogoutMenu;
