import {  Button, message } from 'antd';
import 'pages/CreateUpdateQuiz/createQuiz.scss';
import { Input } from 'antd';
import { DeleteFilled } from '@ant-design/icons';
import {useEffect, useState} from 'react';
import Question from 'pages/CreateUpdateQuiz/Question';
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
  let history = useHistory();
  const [categoriesData, setCategoriesData] = useState([{value: "placeholder", label: "placeholder"}]);
  const [loading, setLoading] = useState(false);
  const [timer, setTimer] = useState(0);

  useEffect(() => {
    const fetchQuiz = async () => {
      try {
          message.loading("Loading your quiz, please wait...");
          let qaData =  await getFullQuiz(id);
          setQuizData(qaData);
          let minutes = Math.round(quizData.quiz.timeLimit /  60000);
          setTimer(minutes);
          message.success("Quiz successfully loaded!");
      }
      catch (error) {
         message.error(!error.response.data.message ? error : error.response.data.message);
         setTimeout(() => {
          history.push("/");
        }, 1500);
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
        message.error(!error.response.data.message ? error : error.response.data.message);
      }
    }
    // call methods for fetching quizzes and categories
    fetchCategories();
    // when id is not provided in url it means that quiz is created, not updated
     if (id)  fetchQuiz();
    // eslint-disable-next-line
  }, []);


  const sendQuiz = async (create, clone) => {
    try {
      if (create) {
        let user = getUser();
        clone.quiz.personId = user.id;
        await createQuizReq(clone);
        message.success("Quiz has been successfully created! Redirecting...");
      }
      else {
        await updateQuizReq(clone);
        message.success("Quiz has been successfully updated! Redirecting...");
      }
      setTimeout(() => {
        history.push("/");
      }, 2500);
    }
    catch (error) {
       message.error(error.response.data.message ? error : error.response.data.message);
    }
    setLoading(false); 
  };

  const updateQuiz = (id, item) => { 
      let clone = JSON.parse(JSON.stringify(quizData));
      clone.qa_response[id] = item;
      setQuizData(clone);
  };

  const deleteQuestion = (id) => {
    let clone = JSON.parse(JSON.stringify(quizData));
    clone.qa_response.splice(id, 1);
    clone.quiz.totalQuestions = clone.qa_response.length;
    setQuizData(clone);
  };

  const onChangeQuizName = e => {
    let clone = JSON.parse(JSON.stringify(quizData));
    clone.quiz.name = e.target.value;
    setQuizData(clone);
  };

  const onChangeTimeLimit = e => {
    let clone = JSON.parse(JSON.stringify(quizData));
    let miliseconds = e.target.value * 60000;
    console.log(miliseconds);
    clone.quiz.timeLimit = miliseconds;
    setQuizData(clone);
    setTimer(e.target.value);
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
    setLoading(true);
    let qa_list = quizData.qa_response;

    if (quizData.qa_response.length <= 1) {
      message.warn("Quiz cannot have only one question!");
      setLoading(false);
      return ;
    }
    for (let i = 0; i < qa_list.length; i++) {
      if (!qa_list[i].question) {
        message.warn("Question #" + String(i + 1) + " cannot be blank!");
        setLoading(false);
        return ;
      }
      if (!qa_list[i].correct_answer || !qa_list[i].options.includes(qa_list[i].correct_answer)) {
        message.warn("Question #" + String(i + 1) + " is missing correct answer!");
        setLoading(false);
        return ;
      }
      if (qa_list[i].options.length < 2) {
        message.warn("Question #" +  String(i + 1) + " has less than 2 answers!");
        setLoading(false);
        return ;
      } 
      for (let j = 0; j < qa_list[i].options.length; j++) {
        if (!qa_list[i].options[j]) {
          message.warn("Answers cannot be blank!");
          setLoading(false);
          return ;
        }
      }
    }

    if (!quizData.quiz.categoryId) {
      message.warn("Category not set!");
      setLoading(false);
      return ;
    }

    if (quizData.quiz.timeLimit < 0) {
      message.warn("Time limit cannot be negative!");
      setLoading(false);
      return ;
    }

    if (!quizData.quiz.name) {
      message.warn("Quiz name cannot be blank!");
      setLoading(false);
      return ;
    }

    let clone = JSON.parse(JSON.stringify(quizData));
    sendQuiz(!id, clone);

  };

  return (
      <div className="cu-container">
       <div className="quiz-details-div">
        <div className="quiz-details-top">
          <p className="quiz-name-label top-label">Quiz name</p> 
          <Input className="quiz-input" type="text" value={quizData.quiz.name} onChange={onChangeQuizName}/> 
          <p className="time-limit-label top-label">Time limit (minutes)</p> 
          <Input className="time-input" type="number" value={timer} onChange={onChangeTimeLimit} min="1"/> 
        </div>
          <div className="quiz-details-top quiz-details-bottom">
            <p className="category-label top-label">Category</p> 
            <Select id="cat-selector" options={categoriesData} onChange={onChangeCategory} placeholder="Select category..." />
            <button  className="ans-btn add-qn-btn"  onClick={onAddQuestion}>Add question</button>
          </div>
       </div>
       <div className="question-list">
        {quizData.qa_response.map(item => {
            let id = quizData.qa_response.indexOf(item);
            return (
              <div className="question-item-outer">
                <div className="question-item-top">
                  <p className="question-title">Question #{id + 1}</p>  
                  <button  className="ans-btn del-qn-btn icon-btn"  onClick={() => deleteQuestion(id)}><DeleteFilled /></button>
                </div>
                <Question key={id} question={item} inx={id} updateQuiz={updateQuiz} /> <br></br> 
              </div>);
        })}
       </div>
        <Button onClick={onSubmitQuiz} loading={loading} className="sub-btn"> Submit quiz</Button>
        </div>
  );
};

export default CreateUpdateQuiz;
