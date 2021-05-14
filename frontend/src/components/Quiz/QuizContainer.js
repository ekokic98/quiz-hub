import React from 'react'
import '../../assets/css/QuizContainer.css'
import QuizDetails from './QuizDetails'
import Question from './Question'
import Answers from './Answers'
import * as tmp from './tempConstants'
import { useState } from 'react'

const QuizContainer = () => {
    const [questionIndex, setQuestionIndex] = useState(0)
    const [finalAnswer, setFinalAnswer] = useState({"answerHistory": [true, false, true], "totalQuestions": tmp.TEMP.length})
    
    //functions
    const getQuestion = (index) => tmp.TEMP[index].name
    
    
    const getAnswers = (index) => { 
        console.log("...........")
        let allAnswers = tmp.TEMP[index].incorrectAnswers.slice()
        allAnswers.push(tmp.TEMP[index].correctAnswer)
        return {"correctAnswer" : tmp.TEMP[index].correctAnswer, "all": allAnswers, onSelectedAnswer}
     }

    const onSelectedAnswer = (task) => {
        if (questionIndex + 1 == tmp.TEMP.length) {}
        else setQuestionIndex(questionIndex + 1)
      }

    return (
        <div className='div-grid'>
            <QuizDetails  details={finalAnswer}/>
            <Question  question={getQuestion(questionIndex)}/>
            <Answers  answers={getAnswers(questionIndex)}/>   
        </div>
    )
}

export default QuizContainer;

