/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj5tztriviagame;

import com.sun.prism.paint.Color;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mason
 */
public class AnswerViewController extends Switchable implements Initializable {

    @FXML
    public Text questionText;
    @FXML
    public Text selectedAnswerText;
    @FXML
    public Text correctAnswerText;
    @FXML
    private AnchorPane anchorPane;
    
    private String question;
    private String selectedAnswer;
    private String correctAnswer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    } 
    
    public void setup(){
        question = questionText.getText();
        selectedAnswer = selectedAnswerText.getText();
        correctAnswer = correctAnswerText.getText();
        anchorPane.setStyle("-fx-base: #FFFFFF");
        
        if(selectedAnswer.equals(correctAnswer)){
            anchorPane.setStyle("-fx-base: #00FF00");
            System.out.println("correct answer selected");
        }
        else {
            System.out.println("incorrect answer selected");
            anchorPane.setStyle("-fx-base: #FF0000");
        }
    }

    @FXML
    private void quitButtonPressed(ActionEvent event) {
        Alert quit = new Alert(AlertType.CONFIRMATION);
        quit.setTitle("Quit Confirmation");
        quit.setHeaderText("Are you sure you would like to quit?");
        
        Optional<ButtonType> result = quit.showAndWait();
        
        if(result.get().equals(ButtonType.OK)){
            Platform.exit();
        }
    }

    @FXML
    private void newQuestionButtonPressed(ActionEvent event) {
        Switchable.switchTo("QuestionView");
        
        //setup new question
        QuestionViewController controller = (QuestionViewController)getControllerByName("QuestionView");
        if (controller != null) {
            controller.ready();
        }
    }

    @FXML
    private void saveQuestionButtonPressed(ActionEvent event) {
        //The code using the FileChooser was taken from the AudioViz challenge.
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(primaryStage);
        try{
            PrintWriter out = new PrintWriter(file);
            out.println("Question: " + question);
            out.println("Selected Answer: " + selectedAnswer);
            out.println("Correct Answer: " + correctAnswer);
            out.close();
            
            Alert save = new Alert(AlertType.INFORMATION);
            save.setTitle("Save Successful");
            save.setHeaderText("Your question was successfully saved to: " + file);
            save.showAndWait();
            
        } catch (Exception ex){
            System.out.println("Exception: " + ex);
        }
    }
    
}
