package flowershop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    private TextField txtUName;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button LoginBtn;
    @FXML
    private Button ResetBtn;
    @FXML
    private Button Registerbtn1;
    @FXML
    private Button closebtn;
    @FXML
    private TextField txtemail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
    }    

   @FXML
    private void LoginBtnPressed(ActionEvent event) {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=testdb;user=sa;password=p@ssword13";
            Connection con=DriverManager.getConnection(url);
            String sql="Select * from register where mail=? and password=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, txtemail.getText());
            pst.setString(2,txtPass.getText());
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                
            }else{
                JOptionPane.showMessageDialog(null, "Username or Password not Correct");
                txtUName.setText("");
                txtPass.setText("");
            }
            con.close();
        }catch(Exception e){
            
        }
    }

    @FXML
    private void ResetBtnPressed(ActionEvent event) {
    }

    @FXML
    private void Register1BtnPressed(ActionEvent event) throws IOException {
         ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
   
    }
    @FXML
    private void Closebtnpressed(ActionEvent event) {
         ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }    
}