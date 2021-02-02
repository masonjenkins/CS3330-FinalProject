/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mj5tztriviagame;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author Mason
 */
public class LaunchScreenController extends Switchable implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void beginGame(ActionEvent event) {
        Switchable.switchTo("QuestionView");
    }

    @FXML
    private void aboutButtonPressed(ActionEvent event) {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("About");
        about.setContentText("Developed by Mason Jenkins for the CS3330 Final Project at the University of Missouri.\n"
                + "This project uses the Open Trivia Database API.\n"
                + "HOW TO PLAY:\n"
                + "Click the begin game button and then a question with the answer choices will appear.\n"
                + "Select your best guess and then you will find out by the color of the screen if you got it correct or incorrect.\n"
                + "On the screen that displays the answer, you will have the option to save the question, correct answer, as well as your answer to a text file.\n"
                + "If you would like to have a new question displayed, click the New Question button.\n"
                + "If you would like to quit the game, click the quit game button and then confirm you would like to quit.");
        about.showAndWait();
    }
    
    
    
}
