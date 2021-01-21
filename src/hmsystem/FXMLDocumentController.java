/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmsystem;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Jayakuru
 */
public class FXMLDocumentController implements Initializable {
    DatabaseHandler handler;
    private Label label;
    @FXML
    private JFXTextField Emp_id;
    @FXML
    private JFXTextField Emp_name;
    
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    
    public ToggleGroup ToggleGroup1;
    @FXML
    private ComboBox Emp_posi;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler=DatabaseHandler.getInstance();
        ToggleGroup1 = new ToggleGroup();
       this.female.setToggleGroup(ToggleGroup1);
       this.male.setToggleGroup(ToggleGroup1);
       
       Emp_posi.getItems().add("Doctor");
       Emp_posi.getItems().addAll("Nurse","Pharmecist","Manager");
       
       male.setToggleGroup(ToggleGroup1);

       female.setToggleGroup(ToggleGroup1);
    
    }    

    @FXML
    private void SaveEmployee(ActionEvent event) {
        String Empid = Emp_id.getText();
        String Empname= Emp_name.getText();
        String posi= Emp_posi.getValue().toString();
        String gender = null;
        if(this.ToggleGroup1.getSelectedToggle().equals(this.male)){
            
                gender= "Male";
            }
                if(this.ToggleGroup1.getSelectedToggle().equals(this.female)){
            
                gender="Female";
             }
                
          String qu = "INSERT INTO EMPLOYEE VALUES ("
                  +"'" + Empid +"',"
                  +"'" + Empname +"',"
                  +"'" + posi +"',"
                  +"'" + gender +"',"
                  +"" + true +""
                 + ")";
            System.out.println(qu);

            if(handler.execAction(qu)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("successfull");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("failed");
                alert.showAndWait();
            }
    }

    @FXML
    private void Cancel(ActionEvent event) {
    }
    
}
