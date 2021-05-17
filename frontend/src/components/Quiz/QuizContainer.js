import React from 'react'
import '../../assets/css/QuizContainer.css'
import QuizDetails from './QuizDetails'
import Question from './Question'
import Answers from './Answers'
import * as tmp from './tempConstants'
import { useState } from 'react'

const QuizContainer = () => {
    const [questionIndex, setQuestionIndex] = useState(0)
    const [finalAnswer, setFinalAnswer] = useState({"answerHistory": Array(tmp.TEMP.length).fill(null), "totalQuestions": tmp.TEMP.length, "pauseTimer": true})

    const onTimeExpired = (currentScore) => {
        // all unanswered questions (null) are replaced with false
        let answerHistoryCopy = finalAnswer.answerHistory.map(n => n == null ? false : n )
        console.log(currentScore)
        console.log(answerHistoryCopy)

        console.log("processing your results...")
        // invoking callback to store results to database and return to main screen
        // processResults(currentScore...)
    }
    
    //functions
    const getQuestion = (index) => tmp.TEMP[index].name
    
    
    const getAnswers = (index) => { 
        let allAnswers = tmp.TEMP[index].incorrectAnswers.slice()
        allAnswers.push(tmp.TEMP[index].correctAnswer)
        return {"correctAnswer" : tmp.TEMP[index].correctAnswer, "all": allAnswers, onSelectedAnswer}
     }

    const onSelectedAnswer = (task) => {
        console.log("in onSelectedAnswer")
        if (questionIndex + 1 === tmp.TEMP.length) {}
        else setQuestionIndex(questionIndex + 1)
        console.log(getQuestion(questionIndex))
      }

    return (
        <div className='div-grid'>
            <QuizDetails  details={finalAnswer} quizHandler={onTimeExpired}/>
            <Question  question={getQuestion(questionIndex)}/>
            <Answers  answers={getAnswers(questionIndex)}/>   
        </div>
    )
}

export default QuizContainer

