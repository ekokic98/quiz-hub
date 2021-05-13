import React from 'react'
import '../../assets/css/Answer.css'

const Answers = ({answers}) => {

    const loadAnswers = (list) => {
        console.log(list)
        let answerButtons = []
        list.forEach(element => {
            answerButtons.push(<button class="ansButtons" onClick={answers.onSelectedAnswer}>{element}</button>)
        });
        return answerButtons
    }
    return (
        <div id="answer">
            <div id="answerSection">
            {loadAnswers(answers.all)}
            </div>
        </div>
    )
}

export default Answers
