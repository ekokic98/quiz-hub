import React from 'react';
import { Col, Dropdown, Row, Input } from "antd";
import Logo from 'assets/images/Logo.svg';
import LoginMenu from 'components/LoginMenu';
import LogoutMenu from 'components/LogoutMenu';
import { useHistory } from 'react-router-dom';
import { homeUrl, categoriesUrl, adminUrl, searchUrl } from "utilities/appUrls";
import { getUser } from "utilities/localStorage";
import { useUserContext } from "AppContext";

import './header.scss';

const { Search } = Input;

const Header = () => {
    const history = useHistory();
    const { loggedIn } = useUserContext();

    const onSearch = async (value) => {
        history.push(searchUrl + "?name=" + value);
    }

    return (
        <Row className='header-container' align='middle'>
            <Col span={4} offset={1} className="logo-col" onClick={() => history.push(homeUrl)}>
                <img src={Logo} alt="Logo" height='80'/>
                <h1 style={{ marginTop: 0 }}>QuizHub</h1>
            </Col>
            <Col span={6}>
                <Search
                    placeholder="Search for a quiz"
                    allowClear
                    enterButton="Search"
                    size="large"
                    onSearch={onSearch}
                    className="search-button"
                />
            </Col>
            { loggedIn && getUser().roles.includes("ROLE_ADMIN") ?
                <Col span={2} offset={11}>
                    <h3 onClick={() => history.push(adminUrl)}>ADMIN</h3>
                </Col>
                : null
            }
            <Col span={2} offset={loggedIn && getUser().roles.includes("ROLE_ADMIN") ? 0 : 9}>
                <h3 onClick={() => history.push(categoriesUrl)}>CATEGORIES</h3>
            </Col>
            <Col span={2}>
                { loggedIn ?
                    <Dropdown overlay={<LogoutMenu history={history} />} className="login-dropdown">
                        <h3 className="ant-dropdown-link" onClick={e => e.preventDefault()}>
                            { getUser().username }
                        </h3>
                    </Dropdown> :
                    <Dropdown overlay={<LoginMenu history={history} />} className="login-dropdown">
                        <h3 className="ant-dropdown-link" onClick={e => e.preventDefault()}>
                            MY ACCOUNT
                        </h3>
                    </Dropdown> }
            </Col>
        </Row>
    );
}

export default Header;
