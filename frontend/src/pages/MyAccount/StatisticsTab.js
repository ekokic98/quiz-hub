import React, { useEffect, useState } from 'react';
import { message, Spin } from "antd";
import { getAllScoresByUser } from "../../api/property/score";
import StatisticsCard from "../../components/StatisticsCard";

const StatisticsTab = () => {

    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                setData(await getAllScoresByUser());
                setLoading(false);
            } catch (error) {
                setLoading(false);
                message.warning("You haven't played any quizzes", 3);
            }
        }
        fetchData();
    }, []);

    return (
        <div className='second-tab'>
            {loading ? <Spin size="large"/> :
                <StatisticsCard data={data}/>
            }
        </div>
    )
}

export default StatisticsTab;
