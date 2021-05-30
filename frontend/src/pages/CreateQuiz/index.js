import {  Button } from 'antd';
import 'pages/CreateQuiz/createQuiz.scss';


import {useEffect, useState} from 'react';
import Question from 'pages/CreateQuiz/Question';
import { useHistory, useParams } from "react-router-dom";
import {  getFullQuiz } from "api/quiz/qa";
import {  updateQuizReq, createQuizReq } from "api/quiz/quiz";
import {  getAllCategories } from "api/quiz/category";
import Select from 'react-select';
import {getUser} from 'utilities/localStorage';

const QUESTION_PLACEHOLDER = {
    "question_id": "5555555-5555-5555-5555-555555555", "category": "Category", "type": "multiple",
    "question": "New question", "correct_answer": "Answer #1", "options": [  "Answer #1","Answer #2"]
};

const BLANK_QUIZ = {
  "qa_response": [ QUESTION_PLACEHOLDER ], "quiz" : {"id": null, "personId": null,"tournamentId": null,"categoryId": null,
      "name": "New quiz","dateCreated": null,"timeLimit": 0,"totalQuestions": 0
  }
};


const CreateUpdateQuiz = () => {
  const [quizData, setQuizData] = useState(BLANK_QUIZ);
  const {id} = useParams();

  const [categoriesData, setCategoriesData] = useState([{value: "placeholder", label: "placeholder"}]);
  const [errorLabel, setErrorLabel] = useState("");

  useEffect(() => {
    fetchCategories();

    // when id is not provided it means that quiz is created not updated
     if (id)  fetchQuiz();
     
  }, []);

  const fetchQuiz = async () => {
    try {
        let qaData =  await getFullQuiz(id);
        setQuizData(qaData)
    }
    catch (error) {
       console.warn(error.response.data.message);
    }
  }

  const fetchCategories = async () => {
    try {
        let categories =  await getAllCategories();
        let reshaped = categories.map(item => {
          return {...item, value: item.id, label: item.name};
        });
        setCategoriesData(reshaped);
    }
    catch (error) {
       console.warn(error.response.data.message);
    }
  }

  const sendQuiz = async (create, clone) => {
    try {
      if (create) {
      let user = getUser();
      clone.quiz.personId = user.id;
       await createQuizReq(clone);
      }
      else {
       await updateQuizReq(clone);
      }
      alert("success!");
    }
    catch (error) {
       console.warn(error.response.data.message);
       alert("fail!");
    }
  }

  const updateQuiz = (id, item) => {
      let clone = JSON.parse(JSON.stringify(quizData));
      clone.qa_response[id] = item;
      setQuizData(clone);
  }

  const deleteQuestion = (id) => {
    let clone = JSON.parse(JSON.stringify(quizData));
    clone.qa_response.splice(id, 1);
    clone.quiz.totalQuestions = clone.qa_response.length;
    setQuizData(clone);
  }

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
    clone.qa_response.push(QUESTION_PLACEHOLDER);
    clone.quiz.totalQuestions = clone.qa_response.length;
    setQuizData(clone);
  };

  const onChangeCategory = (e) => {
    let clone = JSON.parse(JSON.stringify(quizData));
    clone.quiz.categoryId = e.id;
    setQuizData(clone);
  };

  const onSubmitQuiz = () => {
    let qa_list = quizData.qa_response;
    for (let i = 0; i < qa_list.length; i++) {
      if (!qa_list[i].question) {
        setErrorLabel("question empty error");
        return ;
      }
      if (!qa_list[i].correct_answer || !qa_list[i].options.includes(qa_list[i].correct_answer)) {
        setErrorLabel("question missing correct  answer");
        return ;
      }
      if (qa_list[i].options.length < 2) {
        setErrorLabel("question must have at least 2 answers!");
        return ;
      } 
      for (let j = 0; j < qa_list[i].options.length; j++) {
        if (!qa_list[i].options[j]) {
          setErrorLabel("answer cannot be null");
          return ;
        }
      }
    }

    if (!quizData.quiz.categoryId) {
      setErrorLabel("category not set");
      return ;
    }

    if (!quizData.quiz.name) {
      setErrorLabel("quiz name not set");
      return ;
    }
    let clone = JSON.parse(JSON.stringify(quizData));
    sendQuiz(!id, clone);
    setErrorLabel("");
  };

  return (
      <>
       Quiz name: <input type="text" value={quizData.quiz.name} onChange={onChangeQuizName}/> <br/>  
       Minutes: <input type="number" value={quizData.quiz.timeLimit} onChange={onChangeTimeLimit} min="1"/> 
       <Select options={categoriesData} onChange={onChangeCategory} />
       <button  className="ans-btn"  onClick={onAddQuestion}>Add question</button><br/> <br/> 
       
        {quizData.qa_response.map(item => {
            let id = quizData.qa_response.indexOf(item);
            return (
            <>Question #{id + 1}  <button  className="ans-btn"  onClick={() => deleteQuestion(id)}>Delete question</button>
            <Question key={id} question={item} inx={id} updateQuiz={updateQuiz} /> <br></br> </>);
        })}
      
        <Button onClick={onSubmitQuiz}> Submit quiz</Button>
        <p id="err-label" >{errorLabel}</p>
        </>
  );
};

export default CreateUpdateQuiz;
