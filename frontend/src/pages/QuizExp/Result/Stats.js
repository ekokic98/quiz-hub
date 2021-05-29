import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import {Segment, Header, Button} from 'semantic-ui-react';

//import ShareButton from '../ShareButton';
import {calculateScore, calculateGrade, timeConverter} from '../../../utilities/quizUtils';
import { message, Rate } from "antd";
import { addRating, getRatingByUserAndQuiz } from "api/property/rating";
import { getUser } from "utilities/localStorage";
import { useParams } from "react-router-dom";

const Stats = ({
                   totalQuestions,
                   correctAnswers,
                   timeTaken,
                   replayQuiz,
                   exitQuiz
               }) => {
    const user = getUser();
    const { id } = useParams();
    const score = calculateScore(totalQuestions, correctAnswers);
    const {grade, remarks} = calculateGrade(score);
    const {hours, minutes, seconds} = timeConverter(timeTaken);

    const [rating, setRating] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            if (user) {
                try {
                    const response = await getRatingByUserAndQuiz(user.id, id);
                    setRating(response?.rate);
                } catch (ignored) {
                }
            }
        }

        fetchData();
    }, [id, user])

    const rateQuiz = async (newRating) => {
        try {
            setRating(newRating);
            await addRating({
                person: user.id,
                quiz: id,
                rate: newRating
            });
            message.success("You have successfully rated the quiz");
        } catch (error) {
            message.warning(error.response.data.message);
        }
    }

    return (
        <Segment>
            <Header as="h1" textAlign="center" block>
                {remarks}
            </Header>
            <Header as="h2" textAlign="center" block>
                Grade: {grade}
            </Header>
            <Header as="h3" textAlign="center" block>
                Total Questions: {totalQuestions}
            </Header>
            <Header as="h3" textAlign="center" block>
                Correct Answers: {correctAnswers}
            </Header>
            <Header as="h3" textAlign="center" block>
                Your Score: {score}%
            </Header>
            <Header as="h3" textAlign="center" block>
                Passing Score: 50%
            </Header>
            <Header as="h3" textAlign="center" block>
                Time Taken:{' '}
                {`${Number(hours)}h ${Number(minutes)}m ${Number(seconds)}s`}
            </Header>
            <div style={{ marginTop: 35, position: 'relative' }}>
                <Button
                    primary
                    content="Play Again"
                    onClick={replayQuiz}
                    size="big"
                    icon="redo"
                    labelPosition="left"
                    style={{marginRight: 15, marginBottom: 8}}
                />
                <Button
                    color="teal"
                    content="Back to Home"
                    onClick={exitQuiz}
                    size="big"
                    icon="home"
                    labelPosition="left"
                    style={{marginBottom: 8}}
                />
                {user &&
                    <span style={{ position: 'absolute', marginTop: 10, right: 5, fontSize: 20 }}>
                        Rate
                        <Rate value={rating} style={{ marginLeft: 10, fontSize: 32 }} onChange={rateQuiz} />
                    </span>
                }
                {/*<ShareButton/>*/}
            </div>
        </Segment>
    );
};

Stats.propTypes = {
    totalQuestions: PropTypes.number.isRequired,
    correctAnswers: PropTypes.number.isRequired,
    timeTaken: PropTypes.number.isRequired,
    replayQuiz: PropTypes.func.isRequired,
    exitQuiz: PropTypes.func.isRequired
};

export default Stats;
