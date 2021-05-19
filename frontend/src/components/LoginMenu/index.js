import React from 'react';
import { loginUrl, registerUrl } from "utilities/appUrls";
import Logo from 'assets/images/Logo.svg';
import { Button, Col, Row } from "antd";

import "./loginMenu.scss";

const LoginMenu = ({ history }) => {
    return (
        <div className='login-menu'>
            <Row align='middle' justify='space-around'>
                <img src={Logo} alt="Logo" height={50}/>
                <div>Create QuizHub Account</div>
            </Row>
            <Row>
                <div className='login-menu-body'>Creating an account is free and allows you to save your quiz scores to the leaderboard.</div>
            </Row>
            <Row>
                <Col span={16}>
                    <Button onClick={() => history.push(registerUrl)}>Create account</Button>
                </Col>
                <Col span={8}>
                    <Button onClick={() => history.push(loginUrl)}>Login</Button>
                </Col>
            </Row>
        </div>
    );
}

export default LoginMenu;