package flowershop;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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


public class SupplierController implements Initializable {
    
    private Connection con = null;
    private PreparedStatement pst = null;
     private ResultSet rs=null;
    private ObservableList<supplierslist> data;
    @FXML
    private TextField sName;
    @FXML
    private TextField sPhone;
    @FXML
    private TextField sAddress;
    @FXML
    private TableView<supplierslist> SupplierTable;
    @FXML
    private TableColumn<?, ?> suppId;
    @FXML
    private TableColumn<?, ?> suppName;
    @FXML
    private TableColumn<?, ?> suppAddress;
    @FXML
    private TableColumn<?, ?> suppPhone;
    @FXML
    private Button SaveBtn2;
    @FXML
    private Button UpdateBtn2;
    @FXML
    private Button DeleteBtn2;
    @FXML
    private Button ClearBtn2;
    @FXML
    private Button backbtn;

    

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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        con = db_connection.flowershopConnection(); 
        data=FXCollections.observableArrayList(); 
        setCellTable();
        loadDataFromDatabase();
        setCellViewFromTableToTextField();
    } 

    @FXML
    private void SaveBtn2Click(ActionEvent event) throws SQLException{
        
        pst= con.prepareStatement("insert into Supplier(SupplierName, SupplierAdd, SupplierPhone) values(?,?,?)");
        String suppName = sName.getText();
        String suppAdd = sAddress.getText();
        String suppPhn = sPhone.getText();
        
        try {
                
            pst.setString(1, suppName);
            pst.setString(2, suppAdd);
            pst.setString(3, suppPhn);
            
            int i = pst.executeUpdate();
            if(i == 1){
                alertDialog.display("Info", "Data Inserted Successfully");
                sName.setText("");
                sAddress.setText("");
                sPhone.setText("");
                 setCellTable();
        loadDataFromDatabase();
            } 
            
            } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            pst.close();
        }
    }

    @FXML
    private void updatebtnpressed(ActionEvent event) {
        String sql="Update Supplier set  SupplierName=?, SupplierAdd=? where SupplierPhone=?";
        try {
            
            String suppName = sName.getText();
        String suppAdd = sAddress.getText();
        String suppPhn = sPhone.getText();
            
            pst=con.prepareStatement(sql);
            
            pst.setString(1, suppName);
            pst.setString(2, suppAdd);
            pst.setString(3, suppPhn);
           
          
            int k=pst.executeUpdate();
            if(k==1){
               
                alertDialog.display("Info", "Data Updated Successfully");
                sName.setText("");
                sAddress.setText("");
                sPhone.setText("");
               
                
                 setCellTable();
        loadDataFromDatabase();
            }       
            
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void deletebtnpressed(ActionEvent event) {
        String sql="delete from Customer where SupplierPhone=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1, sPhone.getText());
            int i=pst.executeUpdate();
            if(i==1){
                alertDialog.display("Info", "Data Deleted Successfully");
               
               sName.setText("");
                sAddress.setText("");
                sPhone.setText("");
                 loadDataFromDatabase();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clearbtnpressed(ActionEvent event) {
        sName.setText("");
                sAddress.setText("");
                sPhone.setText("");
    }
    private void setCellTable(){
        suppId.setCellValueFactory(new PropertyValueFactory<>("sidlist"));
        suppName.setCellValueFactory(new PropertyValueFactory<>("snamelist"));
        suppAddress.setCellValueFactory(new PropertyValueFactory<>("saddlist"));
        suppPhone.setCellValueFactory(new PropertyValueFactory<>("sphnlist"));
        
    }
    
    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst=con.prepareStatement("select * from Supplier");
            rs=pst.executeQuery();
            while(rs.next()){
               data.add(new supplierslist(""+rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
      SupplierTable.setItems(data);
    }
private void  setCellViewFromTableToTextField(){
    SupplierTable.setOnMouseClicked(e -> {
       
        
           supplierslist pl=   (supplierslist) SupplierTable.getItems().get(SupplierTable.getSelectionModel().getSelectedIndex());
           sName.setText(pl.getSnamelist());
           sAddress.setText(pl.getSaddlist());
           sPhone.setText(pl.getSphnlist());  
            });
}


    
}
