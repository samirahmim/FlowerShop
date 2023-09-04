
package flowershop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainController implements Initializable {

    @FXML
    private Button productbtn;
    @FXML
    private Button closebtn;
    @FXML
    private Button supplierbtn;
    @FXML
    private Button orderbtn1;
    @FXML
    private Button profitlossbtn;
    @FXML
    private Button customerbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void productbtnpressed(ActionEvent event) throws IOException {
       
                    Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("product.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
     @FXML
    private void orderbtnpressed(ActionEvent event) throws IOException {
         Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML
    private void Closebtnpressed(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }   

    @FXML
    private void supplierbtnpressed(ActionEvent event) throws IOException {
         Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("Supplier.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    

    @FXML
    private void profitlossbtnpressed(ActionEvent event) throws IOException {
         Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("ProfitLoss.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void customerbtnpressed(ActionEvent event) throws IOException {
        Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("customer.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

   
}
