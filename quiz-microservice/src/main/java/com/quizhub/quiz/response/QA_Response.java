package com.quizhub.quiz.response;

import java.util.ArrayList;

public class QA_Response {
    private String question_id;
    private String content;
    private String type;
    private int correctAnswer;
    private ArrayList<String> allAnswers;

    public QA_Response(String question_id, String content, String type, int correctAnswer, ArrayList<String> allAnswers) {
        this.question_id = question_id;
        this.content = content;
        this.type = type;
        this.correctAnswer = correctAnswer;
        this.allAnswers = allAnswers;
    }

    public QA_Response() {
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(ArrayList<String> allAnswers) {
        this.allAnswers = allAnswers;
    }
}
