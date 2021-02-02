/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj5tztriviagame;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mason
 */
public class QuestionViewController extends Switchable implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //private Stage stage;
    
    private TriviaManager triviaManager;
    ArrayList<TriviaQuestion> questions;
    
    ObservableList<String> triviaQuestionItems;
    @FXML
    private Text questionText;
    @FXML
    private Button answerButtonOne;
    @FXML
    private Button answerButtonTwo;
    @FXML
    private Button answerButtonThree;
    @FXML
    private Button answerButtonFour;
    @FXML
    private Text categoryText;
    
    private String question;
    
    private ArrayList<String> answers = new ArrayList<>();
    
    private String correctAnswer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ready();
    }    
    
    public void ready (/*Stage stage*/) {
        //this.stage = stage;
        triviaManager = new TriviaManager();
        triviaQuestionItems = FXCollections.observableArrayList();
        
        setup();
        
        loadQuestion();
    }
    
    private void setup(){
        questionText.setText("");
        categoryText.setText("");
        answerButtonOne.setText("");
        answerButtonTwo.setText("");
        answerButtonThree.setText("");
        answerButtonFour.setText("");
        answerButtonThree.setDisable(false);
        answerButtonFour.setDisable(false);
    }
    
    private void loadQuestion() {
        try{
            triviaManager.load();
        } catch(Exception ex) {
            //displayExceptionAlert(ex);
            System.out.println("Exception line 56: " + ex);
            return;
        }
        
        questions = triviaManager.getTriviaQuestions();
        triviaQuestionItems.clear();
        
        for(TriviaQuestion question : questions){
            triviaQuestionItems.add(question.question);
        }
        
        //triviaQuestionView
        //System.out.println("size of triviaquestionitems: " + triviaQuestionItems.size());
        //System.out.println("size of questions: " + questions.toString());
        //questionText.setText(triviaQuestionItems.get(5));
        
        question = triviaManager.getQuestion();
        
        String decodedQuestion;
        
       // decodedQuestion = URLEncoder.de
        
        questionText.setText(triviaManager.getQuestion());
//        ArrayList<String> answers = new ArrayList<>();
        answers = triviaManager.getAnswers();
        
        String type = triviaManager.getType();
        
        if(type.equals("boolean") && !answers.isEmpty()){
            answerButtonThree.setDisable(true);
            answerButtonFour.setDisable(true);
            answerButtonOne.setText(answers.get(0));
            answerButtonTwo.setText(answers.get(1));
        }
        else if(type.equals("multiple") && !answers.isEmpty()){
            answerButtonOne.setText(answers.get(0));
            answerButtonTwo.setText(answers.get(1));
            answerButtonThree.setText(answers.get(2));
            answerButtonFour.setText(answers.get(3));
        }
        
//        if(!answers.isEmpty()){
//        answerButtonOne.setText(answers.get(0));
//        answerButtonTwo.setText(answers.get(1));
//        answerButtonThree.setText(answers.get(2));
//        answerButtonFour.setText(answers.get(3));
//        }
        
       
        
        categoryText.setText(triviaManager.getCategory());
        
        correctAnswer = triviaManager.getCorrectAnswer();
    }

    @FXML
    private void answerOnePressed(ActionEvent event) {
        Switchable.switchTo("AnswerView");
        
        AnswerViewController controller = (AnswerViewController)getControllerByName("AnswerView");
        if (controller != null) {
            controller.questionText.setText(question);
            controller.selectedAnswerText.setText(answerButtonOne.getText());
            controller.correctAnswerText.setText(correctAnswer);
            controller.setup();
        }
    }

    @FXML
    private void answerTwoPressed(ActionEvent event) {
        Switchable.switchTo("AnswerView");
        
        AnswerViewController controller = (AnswerViewController)getControllerByName("AnswerView");
        if (controller != null) {
            controller.questionText.setText(question);
            controller.selectedAnswerText.setText(answerButtonTwo.getText());
            controller.correctAnswerText.setText(correctAnswer);
            controller.setup();
        }
    }

    @FXML
    private void answerThreePressed(ActionEvent event) {
        Switchable.switchTo("AnswerView");
        
        AnswerViewController controller = (AnswerViewController)getControllerByName("AnswerView");
        if (controller != null) {
            controller.questionText.setText(question);
            controller.selectedAnswerText.setText(answerButtonThree.getText());
            controller.correctAnswerText.setText(correctAnswer);
            controller.setup();
        }
        
    }

    @FXML
    private void answerFourPressed(ActionEvent event) {
        Switchable.switchTo("AnswerView");
        
        AnswerViewController controller = (AnswerViewController)getControllerByName("AnswerView");
        if (controller != null) {
            controller.questionText.setText(question);
            controller.selectedAnswerText.setText(answerButtonFour.getText());
            controller.correctAnswerText.setText(correctAnswer);
            controller.setup();
        }
    }
    
}
