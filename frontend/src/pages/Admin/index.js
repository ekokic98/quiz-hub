import React, {useEffect, useState} from 'react';
import {getAllPersons} from "../../api/person/admin";
import {message, Spin, Table} from "antd";

const Admin = () => {
    const [persons, setPersons] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                setPersons(await getAllPersons());
                setLoading(false);
            } catch (error) {
                setLoading(false);
                message.warning(error.response.data.message);
            }
        }
        fetchData();
    }, []);

    const columns = [
        {
            title: 'First name',
            dataIndex: 'firstName',
            key: 'firstName',
            sorter: (a, b) => {
                return a.firstName.localeCompare(b.firstName);
            }
        },
        {
            title: 'Last name',
            dataIndex: 'lastName',
            key: 'lastName',
            sorter: (a, b) => {
                return a.lastName.localeCompare(b.lastName);
            }
        },
        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email',
            sorter: (a, b) => {
                return a.email.localeCompare(b.email);
            }
        },
        {
            title: 'Username',
            dataIndex: 'username',
            key: 'username',
            sorter: (a, b) => {
                return a.username.localeCompare(b.username);
            }
        },
        {
            title: 'City',
            dataIndex: 'city',
            key: 'city'
        },
        {
            title: 'Country',
            dataIndex: 'country',
            key: 'country'
        },
        {
            title: 'Date created',
            dataIndex: 'dateCreated',
            key: 'dateCreated'
        }
    ];

    return (
        <div>
            <h3> Users </h3>
            {loading ? <Spin size="large"/> : <Table dataSource={persons} columns={columns}/>}
        </div>
    );
}

export default Admin;
