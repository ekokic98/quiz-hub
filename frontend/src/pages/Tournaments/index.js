import React, {useEffect, useState} from 'react';
import { Col, message, Row } from "antd";
import MyCard from "components/MyCard";
import { quizUrl } from "utilities/appUrls";
import { useHistory } from "react-router-dom";
import { getQuizzesForTournament } from "api/quiz/quiz";
import placeholderImage from 'assets/images/placeholder.png';

const Tournaments = (props) => {
    const [quizzes, setQuizzes] = useState([]);
    const history = useHistory();
    const id = props.match?.params?.id;

    useEffect(() => {
        const fetchData = async () => {
            try {
                setQuizzes(await getQuizzesForTournament(id));
            } catch (error) {
                message.warning(error.response.data.message);
            }
        }

        fetchData();
    }, [id])

    return (
        <div>
            <Row gutter={[32, 32]}>
                <Col span={24}>
                    <h2 style={{ textAlign: 'left', marginBottom: 10 }}>
                        QUIZZES
                    </h2>
                </Col>
            </Row>
            <Row gutter={[32, 32]}>
                {quizzes.map(quiz =>
                    <Col xs={12} sm={8} md={4}>
                        <MyCard onClick={() => history.push(quizUrl + "/" + quiz.id)} key={quiz.id} imgSrc={quiz.category !== null ? quiz.category.imageUrl : placeholderImage} title={quiz.name} />
                    </Col>
                )}
                {quizzes.length === 0 &&
                    <Col style={{ textAlign: 'left' }} span={24}>
                        <h2>
                            No quizzes
                        </h2>
                    </Col>
                }
            </Row>
        </div>
    );
}

export default Tournaments;
