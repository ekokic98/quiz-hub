import React from 'react'
import './answer.scss'
import { useState, useEffect } from 'react'

const Answers = ({answers}) => {
    const [correctInx, setCorrectInx] = useState(-1)
    const [pickedInx, setPickedInx] = useState(-1)


    const turnColor = (i) => {
        // return color classes depending on chosen answers
        if (pickedInx !== -1) {
            if (i === correctInx) {
                return "gButton"
            }
            if (i === pickedInx && pickedInx !== correctInx)
                return "rButton"
        }
        return ""
    }

    useEffect(() => {
        // reset index of picked answer, if new question is loaded
        if (pickedInx !== -1) {
            let correct = pickedInx === correctInx
            answers.onSelectedAnswer(correct)

            // show user's answers for 3 seconds
            setTimeout(function () {
                setPickedInx(-1)
                setCorrectInx(-1)
            }, 3000)
        }
    }, [pickedInx, correctInx, answers])


    const handleAnswers = (i) => {
        // if answer is chosen, set picked index and index of correct answer (which triggers field coloring)
        setCorrectInx(answers.all.findIndex((item) => item === answers.correctAnswer))
        setPickedInx(i)
    }

    const lockButton = () => pickedInx !== -1 ? " btnLock" : ""  // lock buttons after choosing answers

    return (
        <div id="answer">
            <div id="answerSection">
                { answers.all.map((element, i) =>
                    <button key={ i }
                            className={ "ansButtons " + turnColor(i) + lockButton() }
                            onClick={ () => handleAnswers(i) }>{ element }</button>) }
            </div>
        </div>
    )
}

export default Answers