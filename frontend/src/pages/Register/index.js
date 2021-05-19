import React, { useState } from 'react';
import { useHistory } from "react-router-dom";
import { Form, Input, Button, Tooltip } from 'antd';
import { QuestionCircleOutlined } from '@ant-design/icons';
import { signUp } from "api/person/auth";
import { setSession } from "utilities/localStorage";
import { useUserContext } from "AppContext";

import "./register.scss";

const Register = () => {
    const history = useHistory();
    const { setLoggedIn } = useUserContext();

    const [loading, setLoading] = useState(false);

    const onFinish = async (values) => {
        try {
            setLoading(true);
            const response = await signUp(values);
            setLoading(false);
            setSession(response);
            history.goBack();
            setLoggedIn(true);
        } catch (ignored) {
        }
    };

    return (
        <div className="register-container">
            <div className="register-title">
                REGISTER
            </div>

            <Form
                name="register"
                onFinish={onFinish}
                scrollToFirstError
                size="large"
                className="registration-form"
            >
                <Form.Item
                    name="firstName"
                    label="First name"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your first name!',
                        },
                        ({getFieldValue}) => ({
                            validator() {
                                if (getFieldValue('firstName').length >= 2 && getFieldValue('firstName').length <= 50) {
                                    return Promise.resolve();
                                }
                                return Promise.reject('First name should contain between 2 and 50 characters!');
                            }
                        })
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="lastName"
                    label="Last name"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your last name!',
                        },
                        ({getFieldValue}) => ({
                            validator() {
                                if (getFieldValue('lastName').length >= 2 && getFieldValue('lastName').length <= 50) {
                                    return Promise.resolve();
                                }
                                return Promise.reject('Last name should contain between 2 and 50 characters!');
                            }
                        })
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="email"
                    label="E-mail"
                    rules={[
                        {
                            type: 'email',
                            message: 'The input is not valid E-mail!',
                        },
                        {
                            required: true,
                            message: 'Please input your E-mail!',
                        }
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="username"
                    label={
                        <span>
                        Username&nbsp;
                            <Tooltip title="What do you want others to call you?">
                            <QuestionCircleOutlined/>
                        </Tooltip>
                    </span>
                    }
                    rules={[
                        {
                            required: true,
                            message: 'Please input your username!',
                            whitespace: true
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name="password"
                    label="Password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your password!',
                        },
                        ({getFieldValue}) => ({
                            validator() {
                                if (getFieldValue('password').length >= 8 && getFieldValue('password').length <= 128) {
                                    return Promise.resolve();
                                }
                                return Promise.reject('Password should contain between 8 and 128 characters!');
                            }
                        })
                    ]}
                    hasFeedback
                >
                    <Input.Password/>
                </Form.Item>

                <Form.Item
                    name="confirm"
                    label="Confirm Password"
                    dependencies={['password']}
                    hasFeedback
                    rules={[
                        {
                            required: true,
                            message: 'Please confirm your password!',
                        },
                        ({getFieldValue}) => ({
                            validator(rule, value) {
                                if (!value || getFieldValue('password') === value) {
                                    return Promise.resolve();
                                }
                                return Promise.reject('The two passwords that you entered do not match!');
                            },
                        }),
                    ]}
                >
                    <Input.Password/>
                </Form.Item>

                <Form.Item>
                    <Button loading={loading} type="primary" htmlType="submit" id="registration-button">
                        Register
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
}

export default Register;
