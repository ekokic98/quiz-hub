import { Form, Input, Button, Space } from 'antd';
import { CheckCircleFilled, DeleteFilled, PlusOutlined, CommentOutlined  } from '@ant-design/icons';
import {useState} from 'react';

const Question = (props) => {
  //  const [qa, setQa] = useState(props.question)
    const qa = props.question
    const index = props.inx
    /*
    {  "question": "How many books are in the Chronicles of Narnia series?",     "options": [
        "6",
        "8",
        "5",
        "7"
      ], "correct_answer": "7"}
    */
    const onFinish = values => {
        console.log('Received values of form:', values);
      };
    

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
            item => {  
            let item_id =  qa.options.indexOf(item);
            return (
            <div className="answer-item">             
                <Input  className="ans-input" key={item_id} type="text" value={item} onChange={(e) => onChangeAnswer(e, item_id)}></Input> 
                <button  className="icon-btn delete-btn"  onClick={() => deleteAnswer(item_id)}><DeleteFilled /></button>
                <button  className={item === qa.correct_answer ? "icon-btn green-btn" : "icon-btn"} onClick={() => setCorrectAnswer(item_id)}><CheckCircleFilled/></button>
            </div>);
            }
           
         )}
        </div>
        
    </div>
    );
}


export default Question;