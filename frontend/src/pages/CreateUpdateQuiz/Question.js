import {  Input } from 'antd';
import { CheckCircleFilled, DeleteFilled, CommentOutlined  } from '@ant-design/icons';


const Question = (props) => {
    const qa = props.question
    const index = props.inx

    const onAdd = () => {
        qa.options.push("New answer #"  + qa.options.length);
        props.updateQuiz(index, qa);
    }

    const onChangeAnswer = (e, i) => {
        let correctInx = qa.options.indexOf(qa.correct_answer);
        qa.options[i] = e.target.value;
        if (i === correctInx) qa.correct_answer = qa.options[i];
        props.updateQuiz(index, qa);
    }

    const setCorrectAnswer = (i) => {
        qa.correct_answer = qa.options[i];
        props.updateQuiz(index, qa);
    }

    const deleteAnswer = (i) => {
        qa.options.splice(i, 1);
        props.updateQuiz(index, qa);
    }

    const onChangeQuestion = (e) => {
        qa.question = e.target.value;
        props.updateQuiz(index, qa);
    }

    return (
    <div className="question-item-inner">
        <div className="qn-ans-section">
            <Input className="qn-input" type="text" value={qa.question} onChange={onChangeQuestion}></Input> 
            <button className="ans-add-btn icon-btn" onClick={onAdd}><CommentOutlined /></button>
        </div>
        <div className="ans-section">
        {qa.options.map(
            (item, inx) => {  
            return ( 
            <div key={inx} className="answer-item">             
                <Input  className="ans-input" type="text" value={item} onChange={(e) => onChangeAnswer(e, inx)}></Input> 
                <button  className="icon-btn delete-btn"  onClick={() => deleteAnswer(inx)}><DeleteFilled /></button>
                <button  className={item === qa.correct_answer ? "icon-btn green-btn" : "icon-btn"} onClick={() => setCorrectAnswer(inx)}><CheckCircleFilled/></button>
            </div>);
            }
           
         )}
        </div>
        
    </div>
    );
}


export default Question;