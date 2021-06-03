import React from 'react';
import { Table } from "antd";
import { Link } from "react-router-dom";
import { quizUrl } from "utilities/appUrls";
import moment from "moment";

import "./leaderboard.scss";

const { Column } = Table;

const Leaderboard = ({ scores, noRank, loading, isTournament, disabled }) => {

    let groupedScore = [];

    if (isTournament) {
        for(const score of scores) {
            const username = score.person.username;
            if (groupedScore.filter(score => score.person.username === username).length) {
                continue;
            }
            const scoresByUser = scores.filter(score => score.person.username === username);
            if (scoresByUser.length === 1) {
                groupedScore.push(score);
                continue;
            }
            const latestDate = scoresByUser.reduce(function(prev, current) {
                return (moment(prev.score.dateScored) > moment(current.score.dateScored)) ? prev : current
            }).score.dateScored;
            let totalPoints = 0;
            for (const scoreByUser of scoresByUser) {
                totalPoints += scoreByUser.score.points;
            }
            groupedScore.push({
                score: {
                    ...score.score,
                    dateScored: latestDate,
                    points: totalPoints
                },
                person: score.person,
                quiz: score.quiz
            })
        }
        groupedScore.sort((s1, s2) => s1.score.points !== s2.score.points ?
            s1.score.points < s2.score.points :
            moment(s1.score.dateScored) > moment(s2.score.dateScored)
        );
    } else {
        groupedScore = scores;
    }

    const data = groupedScore.map((score, i) => {
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
        <div style={{ paddingTop: 10 }}>
            <Table rowClassName={disabled ? "disabled-row" : ""} pagination={{ hideOnSinglePage: true }} loading={loading} dataSource={data}>
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
                { !isTournament &&
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
                }
            </Table>
        </div>
    );
}

export default Leaderboard;
