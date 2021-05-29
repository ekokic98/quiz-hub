import { Form, Input, Button } from 'antd';
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons';
import 'pages/CreateQuiz/createQuiz.scss';
import * as tmp from 'pages/CreateQuiz/quizModel';

import {useEffect, useState} from 'react';
import Question from 'pages/CreateQuiz/Question';

const question_placeholder = {
    "question_id": "5555555-5555-5555-5555-555555555", "category": "Category", "type": "multiple",
    "question": "New question", "correct_answer": "Answer #1", "options": [  "Answer #1","Answer #2"]
  };

const formItemLayoutWithOutLabel = {
  wrapperCol: {
    xs: { span: 24, offset: 0 },
    sm: { span: 20, offset: 4 },
  },
};

const CreateQuiz = () => {
  const [quizData, setQuizData] = useState(tmp.data)

  const updateQuiz = (id, item) => {
      let clone = JSON.parse(JSON.stringify(quizData));
      clone.qa_response[id] = item;
      setQuizData(clone);
  }

  const deleteQuestion = (id) => {
    let clone = JSON.parse(JSON.stringify(quizData));
    clone.qa_response.splice(id, 1);
    setQuizData(clone);
  }

  const onFinish = values => {
      console.log(tmp.data.qa_response);
    console.log('Received values of form:', values);
  };

  const onChangeQuizName = e => {
    let clone = JSON.parse(JSON.stringify(quizData));
    clone.quiz.name = e.target.value;
    setQuizData(clone);
  };

  const onChangeTimeLimit = e => {
    let clone = JSON.parse(JSON.stringify(quizData));
    clone.quiz.timeLimit = e.target.value;
    setQuizData(clone);
  };

  const onAddQuestion = () => {
    let clone = JSON.parse(JSON.stringify(quizData));
    clone.qa_response.push(question_placeholder);
    setQuizData(clone);
}

  return (
    <Form name="dynamic_form_item" {...formItemLayoutWithOutLabel} onFinish={onFinish}>
       Quiz name: <input type="text" value={quizData.quiz.name} onChange={onChangeQuizName}/> <br/>  
       Minutes: <input type="number" value={quizData.quiz.timeLimit} onChange={onChangeTimeLimit}/> 
       <button  className="ans-btn"  onClick={onAddQuestion}>Add question</button><br/> <br/> 
       
        {quizData.qa_response.map(item => {
            let id = quizData.qa_response.indexOf(item);
            return (
            <>Question #{id + 1}  <button  className="ans-btn"  onClick={() => deleteQuestion(id)}>Delete question</button>
            <Question key={id} question={item} inx={id} updateQuiz={updateQuiz} /> <br></br> </>);
        })}
       
        <Form.Item>
        <Button type="primary" htmlType="submit">
          Submit quiz
        </Button>
      </Form.Item>
    </Form>
  );
};

export default CreateQuiz;
