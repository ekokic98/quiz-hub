import React from 'react';
import { Col, Dropdown, Row } from "antd";
import Logo from 'assets/images/Logo.svg';
import LoginMenu from 'components/LoginMenu';
import { useHistory } from 'react-router-dom';
import { homeUrl, categoriesUrl } from "../../utilities/appUrls";
import './header.scss';

const Header = () => {
    const history = useHistory();
    return (
        <Row className='header-container' align='middle'>
            <Col span={4} offset={1} className="logo-col" onClick={() => history.push(homeUrl)}>
                <img src={Logo} alt="Logo" height='80'/>
                <h1>QuizHub</h1>
            </Col>
            <Col span={2} offset={15}>
                <h3 onClick={() => history.push(categoriesUrl)}>CATEGORIES</h3>
            </Col>
            <Col span={2}>
                <Dropdown overlay={LoginMenu} className="login-dropdown">
                    <h3 className="ant-dropdown-link" onClick={e => e.preventDefault()}>
                        LOGIN
                    </h3>
                </Dropdown>
            </Col>
        </Row>
    );
}

export default Header;
