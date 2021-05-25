package com.quizhub.quiz.response;

import java.util.ArrayList;

public class QA_Response {
    private String question_id;
    private String category;
    private String type ;
    private String question;
    private String correct_answer;
    private ArrayList<String> incorrect_answers;
    private ArrayList<String> options;

    public QA_Response(String question_id, String category, String type, String question, String correct_answer, ArrayList<String> incorrect_answers, ArrayList<String> options) {
        this.question_id = question_id;
        this.category = category;
        this.type = type;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
        this.options = options;
    }

    public QA_Response() {
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public ArrayList<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(ArrayList<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}
