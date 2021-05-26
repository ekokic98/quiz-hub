import React, { useState } from 'react'
import { getUser, setSession } from "utilities/localStorage";
import { Button, Form, Input, message, Select, Tooltip } from "antd";
import { updateProfile } from "api/person/auth";
import { cities } from "assets/json/constants";

const EditTab = () => {
    const {firstName, lastName, username, email, city} = getUser();
    const [loading, setLoading] = useState(false);

    const onUpdateHandler = async (values) => {
        try {
            setLoading(true);
            if (values.firstName === firstName && values.lastName === lastName
                && values.email === email && !values.city) {
                setLoading(false);
                return;
            }
            const response = await updateProfile(values);
            message.success("Profile successfully updated");
            setLoading(false);
            setSession(response);
        } catch (error) {
            setLoading(false);
            message.warning(error.response.data.message);
        }
    };

    return (
        <Form
            name="editProfile"
            onFinish={ onUpdateHandler }
            scrollToFirstError
            size="middle"
        >
            <Form.Item
                name="username"
                label="Username"
                initialValue={ username }
            >
                <Input disabled/>
            </Form.Item>

            <Form.Item
                name="firstName"
                label="First name"
                initialValue={ firstName }
                rules={ [
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
                ] }
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="lastName"
                label="Last name"
                initialValue={ lastName }
                rules={ [
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
                ] }
            >
                <Input/>
            </Form.Item>

            <Form.Item
                name="email"
                label="E-mail"
                initialValue={ email }
                rules={ [
                    {
                        type: 'email',
                        message: 'The input is not valid E-mail!',
                    },
                    {
                        required: true,
                        message: 'Please input your E-mail!',
                    }
                ] }
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="City"
                name="city"
            >
                <Select placeholder="Choose your city" defaultValue={city}>
                    { cities.map((city) =>
                        <Select.Option key={ city } value={ city }>
                            { city }
                        </Select.Option>)
                    }
                </Select>
            </Form.Item>

            <Form.Item>
                <Button loading={ loading } type="primary" htmlType="submit">
                    Update
                </Button>
            </Form.Item>
        </Form>
    )
}

export default EditTab;