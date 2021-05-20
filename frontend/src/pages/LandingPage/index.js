import React, { useEffect, useState } from 'react';
import { getAllQuizzes } from "api/quiz/quiz";
import { getAllTournaments } from "api/tournament/tournaments";
import { Col, Row } from "antd";
import MyCard from "components/MyCard";
import { quizUrl } from "utilities/appUrls";
import { useHistory } from "react-router-dom";

const LandingPage = () => {
    const history = useHistory();
    const [quizzes, setQuizzes] = useState([]);
    const [tournaments, setTournaments] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                setQuizzes(await getAllQuizzes(false));
                setTournaments(await getAllTournaments(false));
            } catch (ignored) {
            }
        }

        fetchData();
    }, [])

    return (
        <div className="landing-page-container">
            <Row gutter={[32, 32]}>
                <Col span={24}>
                    <h2 style={{ textAlign: 'left' }}>
                        QUIZZES
                    </h2>
                </Col>
            </Row>
            <Row gutter={[32, 32]}>
                {quizzes.map(quiz =>
                    <Col xs={12} sm={8} md={4}>
                        <MyCard onClick={() => history.push( quizUrl + "/" + quiz.id)} key={quiz.id} imgSrc={quiz?.category?.imageUrl} title={quiz.name} />
                    </Col>
                )}
            </Row>
            <Row gutter={[32, 32]}>
                <Col span={24}>
                    <h2 style={{ textAlign: 'left', marginTop: 20 }}>
                        TOURNAMENTS
                    </h2>
                </Col>
            </Row>
            <Row gutter={[32, 32]}>
                {tournaments.map(tournament =>
                    <Col xs={12} sm={8} md={4}>
                        <MyCard key={tournament.id} title={tournament.name} />
                    </Col>
                )}
            </Row>
        </div>
    );
}

export default LandingPage;
