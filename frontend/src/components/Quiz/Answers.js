import React from 'react'
import '../../assets/css/Answer.css'

const Answers = () => {
    const loadAnswers = (list) => {
        let answerButtons = []
        list.forEach(element => {
            answerButtons.push(<button class="ansButtons">{element}</button>)
        });
        return answerButtons
    }
    return (
        <div id="answer">
            <div id="answerSection">
            {loadAnswers(["Most likely", "Nothing really", "Provided answer might not be.."])}
            </div>
        </div>
    )
}

export default Answers
