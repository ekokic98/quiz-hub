import React from 'react'
import './question.scss'

const Question = ({question}) => {
    return (
        <div id = "question">
            <div id="quizDiv">
                <p id= "quizTitle">Countries and cities</p>
            </div>
            <div id="qTextArea">
                <p>{question}</p>
            </div>
        </div>
    )
}

export default Question