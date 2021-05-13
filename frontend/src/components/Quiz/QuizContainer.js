import React from 'react'
import '../../assets/css/QuizContainer.css'
import QuizDetails from './QuizDetails'
import Question from './Question'
import Answers from './Answers'

const QuizContainer = () => {
    return (
        <div className='div-grid'>
            <QuizDetails />
            <Question />
            <Answers />
            
        </div>
    )
}

export default QuizContainer;

