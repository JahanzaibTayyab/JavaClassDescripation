package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import HelpingResources.MysqlConnect;
import arrayListData.Buttoninfo;
import arrayListData.RelationShip;
import arrayListData.UserDefineMethod;
import arrayListData.VariablClass;
import arrayListData.constrauctor;
import arrayListData.getterClass;
import arrayListData.overrideMethod;
import arrayListData.setterClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class SelectfilesController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextArea textid;
    @FXML
    private JFXButton selectFiles;
    @FXML
    private JFXButton nextButton;
    
    File file;
    FileChooser fc=new FileChooser();
    BufferedReader br;
    List<File> f;
    String line;
    String[] word;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nextButton.visibleProperty().set(false);
        conn=MysqlConnect.ConnectDB();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "TRUNCATE TABLE  fileinfo";
            ResultSet rs = stmt.executeQuery(SQL);  
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException {
        textid.setEditable(false);
        textid.clear();
        nextButton.visibleProperty().set(false);
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java Files","*.java"));
                f =fc.showOpenMultipleDialog(null);
                int i=1;
                    String text="";
         for (File file : f)
        {
             
          String fileinfo ="Insert into fileinfo (filename,path,readfile,sizefile,writefile)values (?,?,?,?,?)";
                try{
                pst=conn.prepareStatement(fileinfo);
                pst.setString(1,file.getName());
                pst.setString(2,file.getPath());
                pst.setString(3,Boolean.toString(file.canRead()));
                pst.setString(4,Boolean.toString(file.canWrite()));
                pst.setString(5,Double.toString(file.length()));
          int check=pst.executeUpdate();
          pst.close();     
            }
            catch(Exception ex)
            {ex.printStackTrace();}      
            textid.setStyle("-fx-font-family: monospace");
            textid.setText("File Infromation \n"+"________________________________\n");
                  text+=(" "+i+" : " +file.getName()+"\n");
            i++;
           // textid.setText("___________________________");
           // textid.setText(" "+i+" : "+file.getName());
        }
         textid.appendText(text);
         nextButton.visibleProperty().set(true);
    }

    @FXML
    private void handleAfterButtonAction(ActionEvent event) {
        loadStage("/views/Dashboard.fxml");
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
