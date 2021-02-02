/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj5tztriviagame;

/*
    I ADAPTED THIS CODE FROM THE NYT VIEWER LECTURE. SOME OF IT DOES NOT BELONG TO ME.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
//import org.jsoup.Jsoup;
//import org.apache.commons.lang3.text.translate.CharSequenceTranslator;


/**
 *
 * @author Mason
 */
public class TriviaManager implements NeededQuestionMaterials {
    
    private final String apiUrlString = "https://opentdb.com/api.php?amount=1";
    
    //private final String apiUrlString = "https://opentdb.com/api.php?amount=1&type=boolean";
    
    
    private ArrayList<TriviaQuestion> triviaQuestion;
    //private ArrayList testQuestion;
    private URL url;
    private JSONArray incorrectAnswers;
    public ArrayList<String> answers = new ArrayList<>();
    private String type;
    private String category;
    private String convertedQuestion;
    private String convertedCorrectAnswer;
    
    public TriviaManager() {
        triviaQuestion = new ArrayList<>();
    }
    
    public void load() throws Exception {
        
        String jsonString = "";
        
        try {
            url = new URL(apiUrlString);
        } catch(MalformedURLException muex) {
            throw muex;
        }
        
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            
            String inputLine;
            
            while((inputLine = in.readLine()) != null){
                jsonString += inputLine;
            }
            
            in.close();
        } catch(IOException ioex){
            throw ioex;
        }
        
        System.out.println("jsonString: " + jsonString);
        
        try{
            parseTriviaQuestion(jsonString);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    private void parseTriviaQuestion(String jsonString) throws Exception {
        triviaQuestion.clear();
        
        if(jsonString == null || jsonString == "") return;
        
        JSONObject jsonObj;
        try {
            jsonObj = (JSONObject)JSONValue.parse(jsonString);

        } catch (Exception ex) {
            throw ex;
        }
        
        if (jsonObj == null) return;
        
        String status = "";
        try {
            status = (String.valueOf(jsonObj.get("response_code")));
        } catch (Exception ex) {
            throw ex;
        }
        if (status == null || !status.equals("0")) {
            throw new Exception("Status returned from API was not OK.");
        }
//        JSONObject response;
//        try {
//            response = (JSONObject)jsonObj.get();
//        } catch (Exception ex) {
//            throw ex;
//        }
        JSONArray docs;
        try {
            docs = (JSONArray)jsonObj.get("results");
        } catch (Exception ex) {
            throw ex;
        }
        for (Object doc : docs) {
            try {
                JSONObject questions = (JSONObject)doc;
                String responseCode = (String)questions.getOrDefault("response_code", "");
                String results = (String)questions.getOrDefault("results", "");
                category = (String)questions.getOrDefault("category", "");
                type = (String)questions.getOrDefault("type", "");
                String difficulty = (String)questions.getOrDefault("difficulty", "");
                String question = (String)questions.getOrDefault("question", "");
                String correctAnswer = (String)questions.getOrDefault("correct_answer", "");
                incorrectAnswers = (JSONArray)questions.getOrDefault("incorrect_answers", "");
                
                //String decodedQuestion CharSequenceTranslator ESCAPE_HTML4(question);

                System.out.println("responseCode: " + responseCode);
                System.out.println("results: " + results);
                System.out.println("category: " + category);
                System.out.println("type: " + type);
                System.out.println("difficulty: " + difficulty);
                System.out.println("question: " + question);
                System.out.println("correctAnswer: " + correctAnswer);
                System.out.println("incorrectAnswers: " + incorrectAnswers);
                System.out.println("---------------------------------------------------\n");
                
                convertedQuestion = Jsoup.parse(question).text();
                convertedCorrectAnswer = Jsoup.parse(correctAnswer).text();
                
                
                TriviaQuestion triviaQuestions = new TriviaQuestion(responseCode, results, category, type, difficulty, convertedQuestion, convertedCorrectAnswer, incorrectAnswers);
                triviaQuestion.add(triviaQuestions);
                
                
                //System.out.println("size of triviaquestion in manager: " + triviaQuestion.size());
            } catch (Exception ex) {
                throw ex;
            }
        }
        
        //setupQuestionUI();
    }
    
    public ArrayList<TriviaQuestion> getTriviaQuestions() {
        return triviaQuestion;
    }
    
    public String getQuestion() {
        return convertedQuestion;
    }
    
    public ArrayList<String> getAnswers() {
        answers.add(convertedCorrectAnswer);
        for(int i = 0; i < incorrectAnswers.size(); i++){
            answers.add(Jsoup.parse(incorrectAnswers.get(i).toString()).text());
        }
        //shuffle idea from https://www.geeksforgeeks.org/shuffle-or-randomize-a-list-in-java/
        Collections.shuffle(answers, new Random());
        return answers;
    }
    
    public String getType() {
        return type;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getCorrectAnswer(){
        return convertedCorrectAnswer;
    }
    
}
