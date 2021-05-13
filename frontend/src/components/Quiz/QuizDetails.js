import { useState } from 'react'
import '../../assets/css/QuizDetails.css'
import Timer from "react-compound-timer";


const QuizDetails = () => {
    const [qsNum, setNum] = useState(1)
    const [totalQsNum, setTotalQs] = useState(10)
    const [totalTime, setTotalTime] = useState(3300000)
    const [score, setScore] = useState(0)

    const createGrid = (v) => {
        var gridArr = []
        for (let i = 0; i < v; i++)
            gridArr.push( <div className="qBox">{i + 1}</div>)
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
