import React, { useState } from 'react';
import { Link, useHistory } from "react-router-dom";
import {Form, Input, Button, Checkbox} from 'antd';
import {UserOutlined, LockOutlined} from '@ant-design/icons';
import { registerUrl } from "utilities/appUrls";
import { login } from "api/person/auth";
import { getRememberInfo, removeRememberInfo, setRememberInfo, setSession } from "utilities/localStorage";
import { useUserContext } from "AppContext";

import "./login.scss";

const Login = () => {
    const history = useHistory();
    const { setLoggedIn } = useUserContext();
    const rememberInfo = getRememberInfo();

    const [loading, setLoading] = useState(false);

    const onFinish = async (values) => {
        try {
            setLoading(true);
            const response = await login(values);
            setLoading(false);
            setSession(response);
            if (values.remember) {
                setRememberInfo(values.username, values.password);
            } else {
                removeRememberInfo();
            }
            history.goBack();
            setLoggedIn(true);
        } catch (ignored) {
        }
    };

    return (
        <div className="login-container">
            <div className="login-title">
                LOGIN
            </div>
            <Form
                name="basic-login"
                initialValues={{username: rememberInfo?.username, password: rememberInfo?.password, remember: true }}
                size="large"
                onFinish={onFinish}
            >
                <Form.Item
                    name="username"
                    rules={[{required: true, message: 'Please input your username!'}]}
                >
                    <Input
                        prefix={<UserOutlined className="site-form-item-icon"/>}
                        placeholder="Username"
                    />
                </Form.Item>

                <Form.Item
                    name="password"
                    rules={[{required: true, message: 'Please input your password!'}]}
                >
                    <Input
                        prefix={<LockOutlined className="site-form-item-icon"/>}
                        type="password"
                        placeholder="Password"
                    />
                </Form.Item>

                <Form.Item>
                    <Form.Item name="remember" valuePropName="checked" noStyle>
                        <Checkbox>Remember me</Checkbox>
                    </Form.Item>
                </Form.Item>

                <Form.Item>
                    <Button loading={loading} type="primary" htmlType="submit" id="login-form-button">
                        Log in
                    </Button>
                    Or <Link to={registerUrl}>register now!</Link>
                </Form.Item>
            </Form>
        </div>
    );
}

export default Login;
