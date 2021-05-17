import React from 'react'
import '../../assets/css/Question.css'

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