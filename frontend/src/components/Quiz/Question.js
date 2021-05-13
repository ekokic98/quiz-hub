import React from 'react'
import '../../assets/css/Question.css'

const Question = ({question}) => {
    return (
        <div id = "question">
            <div id="quizDiv">
                <p1 id= "quizTitle">Countries and cities</p1>
            </div>
            <div id="qTextArea">
                <p1>{question}</p1>
            </div>
        </div>
    )
}

export default Question