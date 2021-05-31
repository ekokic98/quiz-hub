import React, {useState, useEffect} from 'react';

import Layout from '../Layout'; 
import Loader from '../Loader'; 
import Quiz from '../Quiz'; 
import Result from '../Result'; 
import { useHistory, useParams } from "react-router-dom"
import {shuffle} from '../../../utilities/quizUtils'; 
import {  getQuizData } from "api/quiz/qa";
import { getUser } from "utilities/localStorage";
import { LocalDateTime } from 'js-joda';
import { postScore} from "api/property/score";
import { quizUrl } from "utilities/appUrls";


const App = () => {
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState(null);
    const [countdownTime, setCountdownTime] = useState(null);
    const [isQuizStarted, setIsQuizStarted] = useState(false);
    const [isQuizCompleted, setIsQuizCompleted] = useState(false);
    const [resultData, setResultData] = useState(null);
    const {id} = useParams();
    let history = useHistory();

    useEffect(() => {
        const fetchQuestionsAnswers = async () => {
            setLoading(true);
            try {
                let qaData =  await getQuizData(id);
                startQuiz(qaData.qa_response, qaData.quiz.timeLimit / 1000); 
            }
            catch (error) {
               console.warn(error.response.data.message);
            }
        }
        fetchQuestionsAnswers();
    }, [id])

    // prepares user's score for database
    const prepareScore = (resultData) => {
        let userData = getUser();
        console.log(resultData);

        let score = null;
        // is user logged in?
        if (userData != null) {
            score = {correctAnswers: resultData.correctAnswers, dateScored: LocalDateTime.now().toString(), id: null, person: userData.id, 
                points: resultData.correctAnswers * 3, quiz: id, totalTime: resultData.timeTaken}
            console.log(score);
        }
        return score;
    }

    const startQuiz = (data, countdownTime) => {
        setCountdownTime(countdownTime);

        setTimeout(() => {
            setData(data);
            setIsQuizStarted(true);
            setLoading(false);
        }, 1000);
    };

    const endQuiz = resultData => {
        setLoading(true);
        let scoreData = prepareScore(resultData);
        setTimeout(() => {
            setIsQuizStarted(false);
            setIsQuizCompleted(true);
  
            if (scoreData != null) {
            const sendScore = async (scoreData) => {
                try {
                    await postScore(scoreData);
                } catch (error) {
                    console.warn(error.response.data);
                }
            }
            sendScore(scoreData);
            }

            setResultData(resultData);

            setLoading(false);
        }, 2000);
    };

    const replayQuiz = () => {
        setLoading(true);

        const shuffledData = shuffle(data);
        shuffledData.forEach(element => {
            element.options = shuffle(element.options);
        });

        setData(shuffledData);

        setTimeout(() => {
            setIsQuizStarted(true);
            setIsQuizCompleted(false);
            setResultData(null);
            setLoading(false);
        }, 1000);
    };

    const exitQuiz = () => {
        history.push(quizUrl + "/" + id);
    };

    return (
        <Layout>
            {loading && <Loader/>}

            {!loading && isQuizStarted && (
                <Quiz data={data} countdownTime={countdownTime} endQuiz={endQuiz}/>
            )}
            {!loading && isQuizCompleted && (
                <Result {...resultData} replayQuiz={replayQuiz} exitQuiz={exitQuiz}/>
            )}
        </Layout>
    );
};

export default App;



