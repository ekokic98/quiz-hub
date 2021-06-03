import React, {useEffect, useState} from 'react';
import { Col, message, Row } from "antd";
import MyCard from "components/MyCard";
import { quizUrl } from "utilities/appUrls";
import { useHistory } from "react-router-dom";
import { getQuizzesForTournament } from "api/quiz/quiz";
import Leaderboard from "components/Leaderboard";
import { getLeaderboardForTournament, getTournament } from "api/tournament/tournament";
import moment from "moment";

const Tournaments = (props) => {
    const [tournament, setTournament] = useState({});
    const [quizzes, setQuizzes] = useState([]);
    const [scores, setScores] = useState([]);
    const [loading, setLoading] = useState(true);
    const [remainingTime, setRemainingTime] = useState(null);
    const history = useHistory();
    const id = props.match?.params?.id;

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await getTournament(id);
                setTournament(response);
                setQuizzes(await getQuizzesForTournament(id));
                setScores(await getLeaderboardForTournament(id));
                if (moment(response.dateEnd).isSameOrBefore(moment())) {
                    setRemainingTime(0);
                } else {
                    setRemainingTime(moment(response.dateEnd).diff(moment()));
                }
            } catch (error) {
                message.warning(error.response.data.message);
            }
            setLoading(false);
        }

        fetchData();
    }, [id])

    const timer = () => setRemainingTime(remainingTime - 1000 < 0 ? 0 : remainingTime - 1000);

    useEffect(() => {
        if (remainingTime) {
            if (remainingTime <= 0) {
                return;
            }
            const id = setInterval(timer, 1000);
            return () => clearInterval(id);
        }
        // eslint-disable-next-line
    }, [remainingTime]);

    return (
        <div>
            <Row gutter={[32, 32]}>
                <Col span={18}>
                    <h2 style={{ textAlign: 'left', marginBottom: 10 }}>
                        { tournament.name }
                    </h2>
                </Col>
                <Col span={6}>
                    <h2 style={{ textAlign: 'right', marginBottom: 10 }}>
                        { remainingTime !== null ?
                            <span style={ remainingTime === 0 ? { color: "red" } : null }>
                                { remainingTime !== 0 ?
                                    Math.floor(moment.duration(remainingTime).asHours()) + moment.utc(remainingTime).format(":mm:ss") + " left"
                                    : "The tournament has ended!"
                                }
                            </span> :
                            "Loading..."
                        }
                    </h2>
                </Col>
            </Row>
            <Row gutter={[32, 32]}>
                <Col span={12}>
                    <Row gutter={[32, 32]}>
                        {quizzes.map(quiz =>
                            <Col key={quiz.id} xs={24} sm={16} md={8}>
                                <MyCard onClick={() => history.push(quizUrl + "/" + quiz.id)} title={quiz.name} />
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
                </Col>
                <Col span={12}>
                    <Leaderboard disabled={remainingTime === 0} loading={loading} scores={scores} isTournament />
                </Col>
            </Row>
        </div>
    );
}

export default Tournaments;
