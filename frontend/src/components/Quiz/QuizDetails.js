import { useState, useRef, useEffect } from 'react'
import React from 'react'
import '../../assets/css/QuizDetails.css'
import Timer from "react-compound-timer"



const QuizDetails = ({ details, quizHandler }) => {
    const [qsNum, setNum] = useState(1)
    const [totalQsNum, setTotalQs] = useState(details.totalQuestions)
 //   const [totalTime, setTotalTime] = useState(3300000)
    const [score, setScore] = useState(0) 
    const timerRef = useRef(null)
    const [pauseTimer, setPauseTimer] = useState(details.pauseTimer)
    
    useEffect(() => {
        if (pauseTimer == true) {
            timerRef.current.pause()
            setTimeout(function() {
                console.log(timerRef.current)
                timerRef.current.resume()
                console.log("resumed")
                setPauseTimer(false)
            }, 3000)
        }

      }, [pauseTimer]);

    const createGrid = (v) => {
        var gridArr = []
        console.log(details.answerHistory.length)
        for (let i = 0; i < v; i++) {
            var style_classes = "qBox "
            if (details.answerHistory[i] != null)
                style_classes += details.answerHistory[i] ? "gBox" : "rBox"
            gridArr.push(<div key={i} className={style_classes}>{i + 1}</div>)
        }
        return gridArr;
    }

    useEffect(() => {
     console.log("....")
    })

    const handleExpire = () => {
        console.log("time has expired")
        // callback
        quizHandler(score)
    }



    return (
        <div id="quiz-details">
            <div id="top">
                <p>Question {qsNum} of {totalQsNum} </p>
            </div>
            <div id="mid">
                <p className="timer"> Timer </p>
                <br />
                <Timer initialTime={10000} direction="backward"  formatValue={value => `${value < 10 ? `0${value}` : value}`} ref={timerRef}
                    checkpoints={[{time: 0, callback: () => handleExpire(), } ]}>
                    <p className="timer countdown">
                        <Timer.Minutes />:<Timer.Seconds /> 
                    </p>
                </Timer>
            </div>
            <div id="bot">
                <p className="timer" id="score">Score:
                <br />
                    {score}
                </p>
                <div id="qGrid">
                    {createGrid(totalQsNum)}
                </div>
            </div>
     
        </div>
    )
}

export default QuizDetails;


/*                <Timer initialTime={totalTime} startImmediately={true} direction="backward" formatValue={value => `${value < 10 ? `0${value}` : value}`}>
                    <p1 className="timer countdown">
                        <Timer.Minutes />:<Timer.Seconds />
                    </p1>
                </Timer> */