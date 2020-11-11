package com.azamzhon.data.models;

public class QuestionModel {
    private String question;
    private String[] allAnswers;
    private String correctAnswer;
    private String type;

    public QuestionModel(String question, String[] allAnswers, String correctAnswer, String type) {
        this.question = question;
        this.allAnswers = allAnswers;
        this.correctAnswer = correctAnswer;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAllAnswers() {
        return allAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getType() {
        return type;
    }
}
