
package flowershop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;


public class RegisterController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private TextField uname;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField cpass;
    @FXML
    private Button registerbtn;
    @FXML
    private Button backbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void Closebtnpressed(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    @FXML
    private void backbtnpressed(ActionEvent event) throws IOException {
        
                    Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    

    @FXML
    private void registerBtnPressed(ActionEvent event) throws SQLException {
        
        
        boolean isValidEmail=textfieldvalidation.isValidEmail(email);
        if(isValidEmail){
            JOptionPane.showMessageDialog(null, "invalid email id");
                email.setText("");
                
        }
         if(uname.getText().equals("")){
            JOptionPane.showMessageDialog(null, "missing username"); 
            email.setText("");
                uname.setText("");
                pass.setText("");
                cpass.setText("");
        }
        else  if(email.getText().equals("")){
            JOptionPane.showMessageDialog(null, "missing email id"); 
            email.setText("");
                uname.setText("");
                pass.setText("");
                cpass.setText("");
        }
      
        else if(!isValidEmail){
            JOptionPane.showMessageDialog(null, "invalid email id"); 
            email.setText("");
                uname.setText("");
                pass.setText("");
                cpass.setText("");
        } 
        else if(pass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "missing password");  
            email.setText("");
                uname.setText("");
                pass.setText("");
                cpass.setText("");
        }
        else if(cpass.getText().equals("")){
            JOptionPane.showMessageDialog(null, "missing repeat password"); 
            email.setText("");
                uname.setText("");
                pass.setText("");
                cpass.setText("");
        }
            
        else if(!pass.equals(cpass)){
            JOptionPane.showMessageDialog(null, "password don't match");
            email.setText("");
                uname.setText("");
                pass.setText("");
                cpass.setText("");
        }
              
        else{
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                 String url="jdbc:sqlserver://localhost:1433;databaseName=testdb;user=sa;password=p@ssword13";
            Connection con=DriverManager.getConnection(url);
            String insert="insert into register(mail,username,password)values(?,?,?)";
            PreparedStatement pst=con.prepareStatement(insert);
            pst.setString(1, email.getText());
            pst.setString(2,uname.getText());
            pst.setString(3,pass.getText());
            int i=pst.executeUpdate();
            if(i==1){
                 JOptionPane.showMessageDialog(null, "User Registered Successfully!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registtration Failed");
                email.setText("");
                uname.setText("");
                pass.setText("");
                cpass.setText("");
            }
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }
}
    