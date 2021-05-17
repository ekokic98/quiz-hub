import React from 'react'
import '../../assets/css/QuizContainer.css'
import QuizDetails from './QuizDetails'
import Question from './Question'
import Answers from './Answers'
import * as tmp from './tempConstants'
import { useState } from 'react'
import { useHistory } from "react-router-dom"

const QuizContainer = () => {
    const history = useHistory()
    const [questionIndex, setQuestionIndex] = useState(0)
    const [finalAnswer, setFinalAnswer] = useState({"answerHistory": Array(tmp.TEMP.length).fill(null), "totalQuestions": tmp.TEMP.length, "pauseTimer": false, "score": 0})

        
    // getters..
    const getQuestion = (index) => tmp.TEMP[index].name
    
    const getAnswers = (index) => { 
        let allAnswers = tmp.TEMP[index].incorrectAnswers.slice()
        allAnswers.push(tmp.TEMP[index].correctAnswer)
        return {"correctAnswer" : tmp.TEMP[index].correctAnswer, "all": allAnswers, onSelectedAnswer}
     }

     const nextQuestion = () => {
         // after timer resumes QuizDetails component calls this callback
        if (questionIndex !== tmp.TEMP.length - 1) setQuestionIndex(questionIndex + 1)  
        else console.log("done")
    }


    const onTimeExpired = (currentScore) => {
        // all unanswered questions (null) are replaced with false     
        let answerHistoryCopy = finalAnswer.answerHistory.map(n => n == null ? false : n )
        console.log("processing your results...")
        history.push("/") // process results and invoke callback to store results to database and return to main screen  TODO
    }


    const onSelectedAnswer = (correctAnswer) => {
        if (questionIndex === tmp.TEMP.length ) {
            // history.push("/"), user answered on all questions, process results and send them to db
            // TODO
        }
        else {
            let answerTmp = finalAnswer.answerHistory.slice(0)
            let score = finalAnswer.score
            answerTmp[questionIndex] = correctAnswer
            score += correctAnswer ? 3 : 0
            setFinalAnswer({"answerHistory":answerTmp, "totalQuestions": tmp.TEMP.length, "pauseTimer": true, "score": score})
        }
      }

    return (
        <div className='div-grid'>
            <QuizDetails  details={finalAnswer} quizHandler={onTimeExpired} nextQuestion={nextQuestion}/>
            <Question  question={getQuestion(questionIndex)}/>
            <Answers  answers={getAnswers(questionIndex)}/>   
        </div>
    )
}

export default QuizContainer

