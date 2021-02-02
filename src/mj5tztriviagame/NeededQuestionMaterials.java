/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj5tztriviagame;

import java.util.ArrayList;

/**
 *
 * @author Mason
 */
public interface NeededQuestionMaterials {
    public ArrayList<TriviaQuestion> getTriviaQuestions();
    public String getQuestion();
    public ArrayList<String> getAnswers();
    public String getType();
    public String getCategory();
    public String getCorrectAnswer();
}
