import React from 'react'
import '../../assets/css/Answer.css'
import { useState, useEffect } from 'react'

const Answers = ({answers}) => {
     const [correctInx, setCorrect] = useState(-1)
     const [pickedInx, setPicked] = useState(-1)


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
            setTimeout(function() {
                setPicked(-1)
                setCorrect(-1)
            }, 3000)
        }
      }, [pickedInx])

    
    const handleAnswers = (i) => { 
        // if answer is chosen, set picked index and index of correct answer (which triggers field coloring)
        setCorrect(answers.all.findIndex((item) => item === answers.correctAnswer)) 
        setPicked(i)
    }

    const lockButton = () => pickedInx !== -1 ? " btnLock" : ""  // lock buttons after choosing answers
 
    return (
        <div id="answer">
            <div id="answerSection">
            { answers.all.map((element, i) => <button key={i} className={"ansButtons " + turnColor(i) + lockButton()} 
              onClick={() => handleAnswers(i)}>{element}</button>)}
            </div>
        </div>
    )
}

export default Answers