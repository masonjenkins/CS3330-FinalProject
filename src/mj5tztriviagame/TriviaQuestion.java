/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj5tztriviagame;

import org.json.simple.JSONArray;

/**
 *
 * @author Mason
 */
public class TriviaQuestion {
    public String responseCode;
    public String results;
    public String category;
    public String type;
    public String difficulty;
    public String question;
    public String correctAnswer;
    public JSONArray incorrectAnswers;
    
    public TriviaQuestion(String responseCode, String results, String category, String type, String difficulty, String question, String correctAnswer, JSONArray incorrectAnswers){
        this.responseCode = responseCode;
        this.results = results;
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }
    
}
