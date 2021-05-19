import { useState, useRef, useEffect } from 'react'
import React from 'react'
import './quizDetails.scss'
import Timer from "react-compound-timer"


const QuizDetails = ({ details, quizHandler, nextQuestion }) => {
    const [qsNum, setNum] = useState(1)
    const timerRef = useRef(null)
    
    // timer pausing if pauseTimer signal is set
    useEffect(() => { 
        if (details.pauseTimer == true) {
            timerRef.current.pause()

            setTimeout(function() {
                timerRef.current.resume()
                details.pauseTimer = false
                nextQuestion()
            }, 2800)
        }
      }, [details]);


    const createGrid = (v) => {
        var gridArr = []
        for (let i = 0; i < v; i++) {
            var style_classes = "qBox "
            if (details.answerHistory[i] != null)
                style_classes += details.answerHistory[i] ? "gBox" : "rBox"
            gridArr.push(<div key={i} className={style_classes}>{i + 1}</div>)
        }
        return gridArr;
    }


    const handleExpire = () => {
        // time has expired..
        quizHandler() // callback
    }

    return (
        <div id="quiz-details">
            <div id="top">
                <p>Question {qsNum} of {details.totalQuestions} </p>
            </div>
            <div id="mid">
                <Timer initialTime={930000} direction="backward"  formatValue={value => `${value < 10 ? `0${value}` : value}`} ref={timerRef}
                    checkpoints={[{time: 0, callback: () => handleExpire(), } ]}>
                    <p className="timer">
                        Timer <br/>
                        <Timer.Minutes />:<Timer.Seconds /> 
                    </p>
                </Timer>
            </div>
            <div id="bot">
                <p className="timer" id="score">Score:
                <br />
                    {details.score}
                </p>
                <div id="qGrid">
                    {createGrid(details.totalQuestions)}
                </div>
            </div>
     
        </div>
    )
}

export default QuizDetails
