package com.dascalu_luca.finstart;

import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private int correctIndex;
    private String explanation;

    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
    public String getExplanation() { return explanation; }
}