package com.azamzhon.data.models;

import java.util.Date;

public class QuizResult {

    private String category;
    private String difficulty;
    private int correctAnswers;
    private Date createdAt;
    int size;

    public QuizResult(String category, String difficulty, int correctAnswers, Date createdAt,int size) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswers = correctAnswers;
        this.createdAt = createdAt;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}