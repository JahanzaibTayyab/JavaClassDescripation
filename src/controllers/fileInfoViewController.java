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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class fileInfoViewController implements Initializable {
    
    @FXML
    private Label label;
    File file;
    FileChooser fc=new FileChooser();
    BufferedReader br;
    List<File> f;
    String line;
    String[] word;
     ArrayList<String> interfaceinfo = new ArrayList<String>();
        ArrayList<String> extendsinfo = new ArrayList<String>();
        ArrayList<String> classname=new ArrayList<String>();
        ArrayList<VariablClass> variableinfo=new ArrayList<VariablClass>();
        ArrayList<setterClass> setterinfo=new ArrayList<setterClass>();
        ArrayList<getterClass> getterinfo=new ArrayList<getterClass>();
        ArrayList<UserDefineMethod> userdefinemethod=new ArrayList<UserDefineMethod>();
        ArrayList<overrideMethod> overrideemethod=new ArrayList<overrideMethod>();
        ArrayList<constrauctor> constractorinfo=new ArrayList<constrauctor>();
        ArrayList<RelationShip> relationinfo=new ArrayList<RelationShip>();
        ArrayList<Buttoninfo> binfo=new ArrayList<Buttoninfo>();
    @FXML
    private GridPane gridpane;
    List<JFXButton> buttonlist = new ArrayList<JFXButton>();
    int s,i = 0;
    int j = 0;
    //JFXButton button[];
   Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=MysqlConnect.ConnectDB();
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT *  from fileinfo";
            ResultSet rs = stmt.executeQuery(SQL);
            int x=0;
            while (rs.next()) {
                buttonlist.add(new JFXButton(rs.getString("filename")));
            }  
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        for(i=0;i<buttonlist.size();i++)
        {
        gridpane.add(buttonlist.get(i), s, j);
        s++;
            if(i == 2){
                s=0;
                j++; // get to the new row.
            }
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
