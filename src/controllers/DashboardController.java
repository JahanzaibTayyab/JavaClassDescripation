package controllers;

import arrayListData.RelationShip;
import arrayListData.UserDefineMethod;
import arrayListData.VariablClass;
import arrayListData.constrauctor;
import arrayListData.getterClass;
import arrayListData.overrideMethod;
import arrayListData.setterClass;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class DashboardController implements Initializable {
    private PieChart pieChart;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    @FXML
    private JFXComboBox<String> comboxlist;
    @FXML
    private Label totalvariable;
    @FXML
    private Label totalpublicvariable;
    @FXML
    private Label totalprivatemethod;
    @FXML
    private Label totalprotectedvariable;
    @FXML
    private Label totalmethod;
    @FXML
    private Label totalgetter;
    @FXML
    private Label totalsetter;
    @FXML
    private Label constractor;
    @FXML
    private JFXTextArea textid;
    @FXML
    private Label inhertencevalue;
    @FXML
    private Label inheritencevalue;
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
        ObservableList<String> filelist =FXCollections.observableArrayList();
        String path=null;
        String[] word;
        boolean over=false;
        boolean classcheck=true;
    @FXML
    private Label mainclassvalue;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conn= HelpingResources.MysqlConnect.ConnectDB();
        loadlist();
    }
    private void loadlist()
    {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT filename  from fileinfo";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                filelist.add(rs.getString("filename"));
            }
            comboxlist.setItems(filelist);  
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    private void loadStudents() throws FileNotFoundException, IOException
    {
        String text="";
        //System.out.println(path);
        File file = new File(path); 
                if (file.exists()) {
                   textid.setStyle("-fx-font-family: monospace");
                   textid.setText("File Infromation \n"+"________________________________________________________________\n");
                  text+=("File Name : "+file.getName()+"\n");
                  text+=("Path  :"+file.getPath()+"\n");
                  text+=("Writeable:  "+file.canWrite()+"\n");
                  text+=("Readable: "+file.canRead()+"\n");
                  text+=("File size in bytes: "+file.length()+"\n");
    }
           // textid.appendText(text);
                String classnameee=file.getName().substring(0, file.getName().length()-5);
                System.out.println(classnameee);
            BufferedReader br = new BufferedReader(new FileReader(file));   
            String line; 
                while ((line = br.readLine()) != null) 
                {
                  if(line.contains("main(String[] args)"))
                      mainclassvalue.setText("Yes");
                 word=line.split(" "); 
                 for (int j=0;j<word.length;j++)
                {
                    if((word[j].equals("//"))) {
                        break;
                    }
                    if(word[j].equals("@Override"))
                    {
                        over=true;
                    }
//Object relationship logic
                    if(line.contains("= new"))
                    {
                        if(line.contains("<"))
                            break;
                        else
                            relationinfo.add(new RelationShip(line.substring(0, 10),"",line));
                            break;
                    }
//Logic for class
                    if (word[j].equals("public") || word[j].equals("private")|| word[j].equals("protected")) {
                        if (word[j + 1].equals(("class"))) {
                            if(classcheck==true)
                            {
                                 text+=("Class Type : "+word[j]+"\n");
                                 classcheck=false;
                            }
                                classname.add(word[j+2]);
                            if(word[j+3].equals("extends"))
                            {
                                String d=word[j+4];
                                d=d.substring(0, d.length()-1);
                                extendsinfo.add(d);
                                if(j+4==word.length-1)
                                {
                                   break;
                                }
                                else if(word[j+5].equals("implements"))
                                {
                                    for(j=+5;j<word.length;j+=2)
                                    {
                                        if(j+1==word.length-1)
                                        {
                                            d=word[j+1];
                                            d=d.substring(0, d.length()-1);
                                            interfaceinfo.add(d);
                                        }
                                        else
                                        {
                                            interfaceinfo.add(word[j+1]);
                                        }    
                                    }
                                }
                            }
                            else if(word[j+3].equals("implements"))
                            { 
                                if(j+4==word.length-1)
                                {
                                    String d=word[j+4];
                                    d=d.substring(0, d.length()-1);
                                    interfaceinfo.add(d);
                                }
                                else if(word[j+5].equals("extends"))
                                {
                                    String d=word[j+5];
                                    d=d.substring(0, d.length()-1);
                                    extendsinfo.add(d);
                                }
                            }
                        }
//logic for setter and user defined method
                        else if(word[j + 1].equals(("void")))
                        {
                            if(word[j+2].contains("set"))
                                setterinfo.add(new setterClass(word[j],word[j+1],word[j+2]+")"));
                            else
                                userdefinemethod.add(new UserDefineMethod(word[j],word[j+1],word[j+2]+")"));
                        }
//logic for variable , getter and user defined function
                        else if(word[j + 1].equals(("int"))||word[j + 1].equals(("double"))||word[j + 1].equals(("float"))||word[j + 1].equals(("String"))
                                ||word[j + 1].contains(("Array")))
                        {
                                if(word[j+2].contains("get"))
                                {
                                    getterinfo.add(new getterClass(word[j],word[j+1],word[j+2]));
                                }
                                else
                                {
                                    variableinfo.add(new VariablClass(word[j],word[j+1],word[j+2]));
                                    if(word[j+2].contains("("))
                                    {
                                        userdefinemethod.add(new UserDefineMethod(word[j],word[j+1],word[j+2]+")"));
                                    }
                                }
                        }
//logic for constructor
                        else if((word[j + 1].substring(0, classnameee.length())).equals((classnameee)))
                        {
                            if(word[j + 1].contains("()"))
                                constractorinfo.add(new constrauctor(classnameee,"Null",word[j],"Default"));
                            else
                                constractorinfo.add(new constrauctor(classnameee,line.substring(19, line.length()-1),word[j],"Parameterized"));  
                        }
//logic for override method
                         if(over==true)
                        {
                           overrideemethod.add(new overrideMethod(word[j],word[j+1],word[j+2]));
                           over=false;
                        }                      
                    }
                }
            }
        int t_Variable=0,p_Variable=0,pri_Variable=0,pro_Variable=0,to_method=0,to_setter=0,to_getter=0,to_constructor=0;
        for(int i=0;i<interfaceinfo.size();i++)
                    {
                    text+="Interface Info : "+interfaceinfo.get(i)+"\n";
                    inhertencevalue.setText("Yes");
                    }
                for(int i=0;i<extendsinfo.size();i++)
                {
                    text+="Inheritence Info: "+extendsinfo.get(i)+"\n";
                    inheritencevalue.setText("Yes");
                }
                for(int i=0;i<variableinfo.size();i++)
                {
                    if(variableinfo.get(i).scope.equalsIgnoreCase("public"))
                    {
                        p_Variable++;
                        text+=variableinfo.get(i).toString()+"\n";
                    }
                    else if(variableinfo.get(i).scope.equalsIgnoreCase("private"))
                    {
                        pri_Variable++;
                        text+=variableinfo.get(i).toString()+"\n";
                    }
                    else if(variableinfo.get(i).scope.equalsIgnoreCase("protected"))
                    {
                        pro_Variable++;
                        text+=variableinfo.get(i).toString()+"\n";
                    }
                    totalvariable.setText(Integer.toString(variableinfo.size()));
                }
                totalpublicvariable.setText(Integer.toString(p_Variable));
                totalprivatemethod.setText(Integer.toString(pri_Variable));
                totalprotectedvariable.setText(Integer.toString(pro_Variable));
                for(int i=0;i<setterinfo.size();i++)
                    text+=setterinfo.get(i).toString()+"\n";
                for(int i=0;i<userdefinemethod.size();i++)
                    text+=userdefinemethod.get(i).toString()+"\n";
                for(int i=0;i<getterinfo.size();i++)
                    text+=getterinfo.get(i).toString()+"\n";
                 for(int i=0;i<overrideemethod.size();i++)
                    text+=overrideemethod.get(i).toString()+"\n";
                 for(int i=0;i<constractorinfo.size();i++)
                    text+=constractorinfo.get(i).toString()+"\n";
                for(int i=0;i<relationinfo.size();i++)
                    text+=relationinfo.get(i).toString()+"\n";
 //labes data        
     totalmethod.setText(Integer.toString(setterinfo.size()+userdefinemethod.size()+getterinfo.size()));
     totalgetter.setText(Integer.toString(getterinfo.size()));
     totalsetter.setText(Integer.toString(setterinfo.size()));
     constractor.setText(Integer.toString(constractorinfo.size()));
     textid.appendText(text);
    }

    @FXML
    private void ClassActionButton(ActionEvent event) throws IOException {
        try (    
            Statement stmt = conn.createStatement();) {
            String SQL = "SELECT path  from fileinfo where filename like('"+comboxlist.getValue()+"%')";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                path=rs.getString("path");
            }
            stmt.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }  
        loadStudents();
    }

}
