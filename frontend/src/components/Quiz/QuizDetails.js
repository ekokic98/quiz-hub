import { useState } from 'react'
import React from 'react'
import '../../assets/css/QuizDetails.css'
import Timer from "react-compound-timer"
import ReactDOM from "react-dom"


const QuizDetails = ({details}) => {
    const [qsNum, setNum] = useState(1)
    const [totalQsNum, setTotalQs] = useState(10)
    const [totalTime, setTotalTime] = useState(3300000)
    const [score, setScore] = useState(0)

    const createGrid = (v) => {
        var gridArr = []
        for (let i = 0; i < v; i++) {
            var style_classes = "qBox "
            if (details.answerHistory.length != 0 && i < details.answerHistory.length)
               style_classes += details.answerHistory[i] ? "gBox" : "rBox"
            gridArr.push( <div key={i} className={style_classes}>{i + 1}</div>)
        }
        return gridArr; 
    }

    return (
        <div id="quiz-details">
            <div id="top">
                <p1>Question {qsNum} of {totalQsNum} </p1>
            </div>
            <div id="mid">
            <p1 className="timer"> Timer </p1>
            <br/>
            <Timer initialTime={totalTime} startImmediately={true} direction="backward" formatValue={value => `${value < 10 ? `0${value}` : value}`}>
                <p1 className="timer countdown">
                    <Timer.Minutes />:<Timer.Seconds /> 
                </p1>
            </Timer>
            </div>
            <div id="bot">
                <p1 className="timer" id="score">Score:
                <br/>
                {score}
                </p1>
                <div id="qGrid">
                    {createGrid(10)}
                </div>
            </div>
        </div>
    )
}

export default QuizDetails;
