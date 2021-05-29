import React, { useEffect, useState } from 'react';
import { getQuizData } from "api/quiz/qa";
import { Button, Col, message, Row, Spin } from "antd";
import MyCard from "components/MyCard";
import { quizUrl } from "utilities/appUrls";
import { useHistory } from "react-router-dom";
import { getAllRatingsByQuiz } from "api/property/rating";
import { getAllScoresByQuiz } from "api/property/score";
import Leaderboard from "components/Leaderboard";
import { PlusOutlined, MinusOutlined, StarOutlined, StarFilled } from "@ant-design/icons";
import { followPerson, unfollowPerson } from "api/person/person";
import { getUser, setUser } from "utilities/localStorage";
import { addFavorite, deleteFavorite, getAllFavoritesByQuiz } from "api/property/favorite";
import placeholderImage from 'assets/images/placeholder.png';

import "./quizMain.scss";

const QuizMain = (props) => {
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [quizData, setQuizData] = useState({});
    const [scores, setScores] = useState([]);
    const [ratings, setRatings] = useState([]);
    const [person, setPerson] = useState(getUser());
    const [favorites, setFavorites] = useState([]);

    const quizId = props.match?.params?.id;

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                setQuizData(await getQuizData(quizId));
                setScores(await getAllScoresByQuiz(quizId));
                setRatings(await getAllRatingsByQuiz(quizId));
                setFavorites(await getAllFavoritesByQuiz(quizId));
                setLoading(false);
            } catch (error) {
                setLoading(false);
                message.warning(error.response.data.message);
            }
        }

        fetchData();
    }, [quizId])

    const follow = async () => {
        try {
            const userFollower = person.follows.find(follower => follower.follower.id === quizData.person?.id);
            if (userFollower) {
                await unfollowPerson(userFollower);
                setUser({
                    ...person,
                    follows: person.follows.filter(follower => follower.id !== userFollower.id)
                });
                message.success("You have successfully unfollowed this person");
            } else {
                const response = await followPerson({
                    userId: person.id,
                    followerId: quizData.person?.id
                });
                setUser({
                    ...person,
                    follows: [...person.follows, response]
                });
                message.success("You have successfully followed this person");
            }
            setPerson(getUser());
        } catch (error) {
            message.warning(error.response.data.message);
        }
    }

    const favorite = async () => {
        try {
            const favorite = favorites.find(favorite => favorite.person === person.id && favorite.quiz === quizData.quiz.id);
            if (favorite) {
                await deleteFavorite(favorite.id);
                setFavorites(favorites.filter(f => f.id !== favorite.id));
                message.success("You have successfully unfavorited this quiz");
            } else {
                const response = await addFavorite({
                    person: person.id,
                    quiz: quizData.quiz.id
                });
                setFavorites([...favorites, response]);
                message.success("You have successfully favorited this quiz");
            }
        } catch (error) {
            message.warning(error.response.data.message);
        }
    }

    const isFollowed = person.follows.some(follower => follower.follower.id === quizData.person?.id);
    const isFavorited = favorites.some(favorite => favorite.person === person.id);

    return (
        <div className="quiz-main-container">
            { loading ? <Spin size="large"/> :
                <>
                    <Row gutter={[32, 32]}>
                        <Col span={6}>
                            <MyCard
                                onClick={() => history.push(quizUrl + "/play/" + quizId)}
                                imgSrc={quizData.quiz.category !== null ? quizData.quiz.category.imageUrl : placeholderImage}
                                title={quizData.quiz.name}
                            />
                            { quizData.person &&
                                <div className="created-by-container">
                                    <span>
                                        Created by:
                                        <span>
                                            { " " + quizData.person?.username }
                                        </span>
                                    </span>
                                    { person &&
                                    <Button type={ isFollowed ? "primary" : "default" } className="follow-button"
                                            onClick={ follow }>
                                        { isFollowed ?
                                            <>
                                                <MinusOutlined style={ {marginRight: 5} }/>
                                                Unfollow
                                            </> :
                                            <>
                                                <PlusOutlined style={ {marginRight: 5} }/>
                                                Follow
                                            </>
                                        }
                                    </Button>
                                    }
                                </div>
                            }
                        </Col>
                        <Col span={6}>
                            <div className="quiz-label">
                                Category
                            </div>
                            <div className="quiz-label-text">
                                { quizData.quiz.category ? quizData.quiz.category.name : "No category" }
                            </div>
                            <div className="quiz-label">
                                Times played
                            </div>
                            <div className="quiz-label-text">
                                { scores.length }
                            </div>
                            <div className="quiz-label">
                                Rating
                            </div>
                            <div className="quiz-label-text">
                                { ratings.length !== 0 ? (ratings.map(rating => rating.rate).reduce((prev, curr) => prev + curr) / ratings.length) : "No ratings" }
                            </div>
                            <Button type={ isFavorited ? "primary" : "default" } className="favorite-button" block onClick={ favorite }>
                                { isFavorited ?
                                    <>
                                        <StarFilled style={{ marginRight: 5 }}/>
                                        Unfavorite
                                    </> :
                                    <>
                                        <StarOutlined style={{ marginRight: 5 }}/>
                                        Favorite
                                    </>
                                }
                            </Button>
                        </Col>
                        <Col span={12}>
                            <Leaderboard loading={loading} scores={scores} />
                        </Col>
                    </Row>
                </>
            }
        </div>
    );
}

export default QuizMain;
