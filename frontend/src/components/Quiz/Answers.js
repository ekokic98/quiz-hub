import React from 'react'
import '../../assets/css/Answer.css'
import { useState, useEffect } from 'react'

const Answers = ({answers}) => {
     const [correctInx, setCorrect] = useState(-1)
     const [pickedInx, setPicked] = useState(-1)

    const turnColor = (i) => {
        if (pickedInx !== -1) {
            //do stuff
            if (i === correctInx) {
                return "gButton"
            }
            if (i === pickedInx && pickedInx !== correctInx)
                return "rButton" 
        }
        return ""
    }

    useEffect(() => {
        if (pickedInx !== -1) {
            setTimeout(function() {
                setPicked(-1)
                setCorrect(-1)
                answers.onSelectedAnswer(null)
            }, 3000);  

        }
      }, [pickedInx]);
    
    const handleAnswers = (i) => { 
        setCorrect(answers.all.findIndex((item) => item === answers.correctAnswer)) 
        setPicked(i)
    }
 
    return (
        <div id="answer">
            <div id="answerSection">
            { answers.all.map((element, i) => <button key={i} className={"ansButtons " + turnColor(i)} onClick={() => handleAnswers(i)}>{element}</button>)}
            </div>
        </div>
    )
}

export default Answers