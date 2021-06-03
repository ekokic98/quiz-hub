import React, {useEffect, useState} from 'react';
import { Col, message, Row } from "antd";
import MyCard from "components/MyCard";
import { quizUrl } from "utilities/appUrls";
import { useHistory } from "react-router-dom";
import { getQuizzesByName } from "api/quiz/quiz";

const Search = (props) => {
    const [quizzes, setQuizzes] = useState([]);
    const history = useHistory();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const urlParams = new URLSearchParams(props.location.search);
                setQuizzes(await getQuizzesByName(urlParams.get('name')));
            } catch (error) {
                message.warning(error.response.data.message);
            }
        }

        fetchData();
    }, [props.location.search])

    return (
        <div>
            <Row gutter={[32, 32]}>
                <Col span={24}>
                    <h2 style={{ textAlign: 'left', marginBottom: 10 }}>
                        Search result
                    </h2>
                </Col>
            </Row>
            <Row gutter={[32, 32]}>
                {quizzes.map(quiz =>
                    <Col key={quiz.id} xs={12} sm={8} md={4}>
                        <MyCard onClick={() => history.push(quizUrl + "/" + quiz.id)} key={quiz.id} imgSrc={quiz?.category?.imageUrl} title={quiz.name} />
                    </Col>
                )}
                {quizzes.length === 0 &&
                    <Col style={{ textAlign: 'left' }} span={24}>
                        <h2>
                            No quizzes with that name.
                        </h2>
                    </Col>
                }
            </Row>
        </div>
    );
}

export default Search;
