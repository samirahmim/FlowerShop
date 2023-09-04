
package flowershop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class CustomerController implements Initializable {
    
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs=null;
    private ObservableList<customerslist> data;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField address;
    @FXML
    private TextField phonenum;
    @FXML
    private TableView<customerslist> CustTableView;
    @FXML
    private TableColumn<?, ?> custId;
    @FXML
    private TableColumn<?, ?> fName;
    @FXML
    private TableColumn<?, ?> lName;
    @FXML
    private TableColumn<?, ?> CustAddress;
    @FXML
    private TableColumn<?, ?> phone;
    @FXML
    private Button savebtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    @FXML
    private Button clearbtn;
    @FXML
    private Button backbtn;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       con=db_connection.flowershopConnection();
        data=FXCollections.observableArrayList(); 
        setCellTable();
        loadDataFromDatabase();
        setCellViewFromTableToTextField();
    }    

    @FXML
    private void savebtnclick(ActionEvent event) throws SQLException {
        
        pst = con.prepareStatement("insert into Customer(CustFirstName, CustLastName, CustAdd, CustPhone) values(?,?,?,?)");
        String firstName = fname.getText();
        String lastName = lname.getText();
        String add = address.getText();
        String phn = phonenum.getText();
        try {
                
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, add);
            pst.setString(4, phn);
            
            int i = pst.executeUpdate();
            if(i == 1){
                alertDialog.display("Info", "Data Inserted Successfully");
                fname.setText("");
                lname.setText("");
                address.setText("");
                phonenum.setText("");
                 setCellTable();
        loadDataFromDatabase();
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            pst.close();
        }
    }

    @FXML
    private void updatebtnpressed(ActionEvent event) {
        String sql="Update Customer set  CustFirstName=?, CustLastName=?, CustAdd=? where CustPhone=?";
        try {
            
            String firstname=fname.getText();
            String lastname=lname.getText();
            String add=address.getText();
            String phone=phonenum.getText();
            
            pst=con.prepareStatement(sql);
            
            pst.setString(1, firstname);
            pst.setString(2, lastname);
            pst.setString(3, add);
           pst.setString(4, phone);
          
            int k=pst.executeUpdate();
            if(k==1){
               
                alertDialog.display("Info", "Data Updated Successfully");
                fname.setText("");
                lname.setText("");
                address.setText("");
                phonenum.setText("");
                
                 setCellTable();
        loadDataFromDatabase();
            }       
            
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setCellTable(){
        custId.setCellValueFactory(new PropertyValueFactory<>("funcid"));
        fName.setCellValueFactory(new PropertyValueFactory<>("funcfname"));
        lName.setCellValueFactory(new PropertyValueFactory<>("funclname"));
        CustAddress.setCellValueFactory(new PropertyValueFactory<>("funcaddress"));
        phone.setCellValueFactory(new PropertyValueFactory<>("funcphone"));
    }
    
    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst=con.prepareStatement("select * from Customer");
            rs=pst.executeQuery();
            while(rs.next()){
               data.add(new customerslist(""+rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
      CustTableView.setItems(data);
    }
private void  setCellViewFromTableToTextField(){
    CustTableView.setOnMouseClicked(e -> {
       
        
           customerslist pl=   (customerslist) CustTableView.getItems().get(CustTableView.getSelectionModel().getSelectedIndex());
           fname.setText(pl.getFuncfname());
           lname.setText(pl.getFunclname());
           address.setText(pl.getFuncaddress());
           phonenum.setText(pl.getFuncphone());
        

    
    
            });
}

    @FXML
    private void deletebtnpressed(ActionEvent event) {
        String sql="delete from Customer where CustPhone=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1, phonenum.getText());
            int i=pst.executeUpdate();
            if(i==1){
                alertDialog.display("Info", "Data Deleted Successfully");
               
               fname.setText("");
                lname.setText("");
                address.setText("");
                phonenum.setText("");
                 loadDataFromDatabase();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clearbtnpressed(ActionEvent event) {
        fname.setText("");
                lname.setText("");
                address.setText("");
                phonenum.setText("");
    }

    @FXML
    private void backbtnpressed(ActionEvent event) throws IOException {
         Stage primaryStage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    

}
