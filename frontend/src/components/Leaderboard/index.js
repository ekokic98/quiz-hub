import React from 'react';
import { Table } from "antd";
import { Link } from "react-router-dom";
import { quizUrl } from "utilities/appUrls";
import moment from "moment";

const { Column } = Table;

const Leaderboard = ({ scores, noRank, loading }) => {

    const data = scores.map((score, i) => {
        return {
            key: score.score.id,
            rank: i + 1,
            date: score.score.dateScored,
            points: score.score.points,
            username: score.person.username,
            quiz: score.quiz.name,
            quizId: score.quiz.id
        };
    });

    return (
        <div className="leaderboard-container">
            <Table pagination={{ hideOnSinglePage: true }} loading={loading} dataSource={data}>
                { !noRank && <Column title="Rank" dataIndex="rank" key="rank" /> }
                <Column
                    title="Date"
                    dataIndex="date"
                    key="date"
                    render={(text) => (
                        <div>
                            { moment(text).local().format("D MMMM YYYY [at] HH:mm") }
                        </div>
                    )}
                />
                <Column title="Points" dataIndex="points" key="points" />
                <Column title="Username" dataIndex="username" key="username" />
                <Column
                    title="Quiz"
                    dataIndex="quiz"
                    key="quiz"
                    render={(text, record) => (
                        <Link to={quizUrl + "/" + record.quizId}>
                            { text }
                        </Link>
                    )}
                />
            </Table>
        </div>
    );
}

export default Leaderboard;
