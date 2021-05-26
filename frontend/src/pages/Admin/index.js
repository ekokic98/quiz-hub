import React, {useEffect, useState} from 'react';
import {getAllPersons} from "../../api/person/admin";
import {Button, message, Spin, Table} from "antd";
import jsPDF from "jspdf";
import 'jspdf-autotable';
import {DownloadOutlined} from "@ant-design/icons";

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

    const generateReport = () => {
        let usersForReport = [];
        for (let i = 0; i < persons.length; i++) {
            let userObj = Object.values({
                firstName: persons[i].firstName,
                lastName: persons[i].lastName,
                email: persons[i].email,
                username: persons[i].username,
                city: persons[i].city,
                country: persons[i].country,
                dateCreated: persons[i].dateCreated.substr(8, 2) + "." +
                    persons[i].dateCreated.substr(5, 2) + "." +
                    persons[i].dateCreated.substr(0, 4) + ".",
                timeCreated: persons[i].dateCreated.substr(11, 5) + "h"
            });
            usersForReport.push(userObj);
        }
        const doc = new jsPDF();
        doc.autoTable({
            head: [['First name', 'Last name', 'Email', 'Username', 'City', 'Country', 'Date', 'Time']],
            body: usersForReport
        })
        doc.save('QuizHub-Users.pdf');
    };

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
            {loading ? <Spin size="large"/> :
                <>
                    <Table dataSource={persons} columns={columns}/>
                    <Button onClick={generateReport}>PDF report<DownloadOutlined/></Button>
                </>
            }
        </div>
    );
}

export default Admin;
