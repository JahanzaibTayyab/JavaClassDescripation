package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class ConditionController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextArea textid;
    @FXML
    private JFXButton NextButton;
    @FXML
    private CheckBox checkBox;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        NextButton.visibleProperty().set(false);
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        loadStage("/views/selectfiles.fxml");
    }

    @FXML
    private void actiononcheckbox(ActionEvent event) {
      //  fghjj.
        if(checkBox.isSelected())
        {
            NextButton.visibleProperty().set(true);
        }
    }
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
           // stage.getIcons().add(new Image("/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
