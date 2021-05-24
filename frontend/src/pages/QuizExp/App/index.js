import React, {useState, useEffect} from 'react';

import Layout from '../Layout'; //
import Loader from '../Loader'; //
//import Main from '../Main';
import Quiz from '../Quiz'; //
import Result from '../Result'; //
import { useHistory, useParams } from "react-router-dom"
import {shuffle} from '../../../utilities/quizUtils'; //
import {  getQuizData } from "api/quiz/qa";

const App = () => {
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState(null);
    const [countdownTime, setCountdownTime] = useState(null);
    const [isQuizStarted, setIsQuizStarted] = useState(false);
    const [isQuizCompleted, setIsQuizCompleted] = useState(false);
    const [resultData, setResultData] = useState(null);
    const {id} = useParams()

    useEffect(() => {
        const fetchQuestionsAnswers = async () => {
            setLoading(true);
            console.log(id);
            try {
                let qaData =  await getQuizData(id);
                startQuiz(qaData, 300000);
               
            }
            catch (error) {
               console.warn(error.response.data.message);
            }
        }
        fetchQuestionsAnswers();
    }, [])

    const startQuiz = (data, countdownTime) => {
       //setLoading(true);
        setCountdownTime(countdownTime);

        setTimeout(() => {
            setData(data);
            setIsQuizStarted(true);
            setLoading(false);
        }, 1000);
    };

    const endQuiz = resultData => {
        setLoading(true);

        setTimeout(() => {
            setIsQuizStarted(false);
            setIsQuizCompleted(true);
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

    const resetQuiz = () => {
        setLoading(true);

        setTimeout(() => {
            setData(null);
            setCountdownTime(null);
            setIsQuizStarted(false);
            setIsQuizCompleted(false);
            setResultData(null);
            setLoading(false);
        }, 1000);
    };

    return (
        <Layout>
            {loading && <Loader/>}

            {!loading && isQuizStarted && (
                <Quiz data={data} countdownTime={countdownTime} endQuiz={endQuiz}/>
            )}
            {!loading && isQuizCompleted && (
                <Result {...resultData} replayQuiz={replayQuiz} resetQuiz={resetQuiz}/>
            )}
        </Layout>
    );
};

export default App;

/*
            {!loading && !isQuizStarted && !isQuizCompleted && (
                <Main startQuiz={startQuiz}/>
            )}
*/

