package flowershop;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.table.DefaultTableModel;



public class productController implements Initializable {
    private Connection con = null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private ObservableList<ProductList> data;
    @FXML
    private Button backbtn;

    @FXML
    private TextField prodBarcode;
   
    @FXML
    private TextField prodName;
   
    @FXML
    private TextField prodbprice;
    @FXML
    private TextField prodsprice;
    @FXML
    private Button savebtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    @FXML
    private Button clearbtn;
    @FXML
    private TableView<ProductList> TableView;
     @FXML
    private TableColumn<?, ?> colbarcode;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> bprice;
    @FXML
    private TableColumn<?, ?> sprice;
    private TableColumn<?, ?> barcode2;
    private TableColumn<?, ?> name1;
    @FXML
    private TextField txt_qty;
    @FXML
    private Label error_qty;
    @FXML
    private DatePicker datein_Picker;
    @FXML
    private TableColumn<?, ?> colqty;
    @FXML
    private TableColumn<?, ?> coldatein;
    
    
    private int pid;
    @FXML
    private TextField txtsearch;
    @Override
     public void initialize(URL url, ResourceBundle rb) {
       
        con=db_connection.flowershopConnection();
        data=FXCollections.observableArrayList(); 
        setCellTable();
        loadDataFromDatabase();
        setCellViewFromTableToTextField();
        searchProduct();
    }  
    

    @FXML
    private void savebtnclick(ActionEvent event) throws SQLException {
        
       
         String barcode=prodBarcode.getText();
        String name=prodName.getText();
       
        double bprice=Double.valueOf(prodbprice.getText());
        double sprice=Double.valueOf(prodsprice.getText());
        
        try {
             pst=con.prepareStatement("insert into producttwo(barcode,pname,bprice,sprice)values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
           pst.setString(1, barcode);
            pst.setString(2, name);
            
            pst.setDouble(3, bprice);
            pst.setDouble(4, sprice);
            
            pst.executeUpdate();
            
            rs=pst.getGeneratedKeys();
            rs.next();
            Object key=rs.getObject(1);
            String sql="Insert into stock(pid,Qty,DateIn) values(?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(String.valueOf(key)));
            pst.setInt(2, Integer.parseInt(txt_qty.getText()));
            pst.setDate(3, java.sql.Date.valueOf(datein_Picker.getValue()));
             int k=pst.executeUpdate();
            if(k==1){
               
                alertDialog.display("Info", "Data Inserted Successfully");//e?list e toh haat e dei nai ekhono -.- type korte 
                prodName.setText("");
                prodBarcode.setText("");
                prodbprice.setText("");
                prodsprice.setText("");
                 setCellTable();
        loadDataFromDatabase();
            }       
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            pst.close();
        }
        
    }
    
     @FXML
    private void updatebtnpressed(ActionEvent event) {
        String sql="Update producttwo set  pname=?,bprice=?,sprice=? where barcode=?";
        try {
            
            String name=prodName.getText();
            double bprice=Double.valueOf(prodbprice.getText());
            double sprice=Double.valueOf(prodsprice.getText());
            String barcode=prodBarcode.getText();
            pst=con.prepareStatement(sql);
            
            pst.setString(1, name);
            pst.setDouble(2, bprice);
            pst.setDouble(3, sprice);
            pst.setString(4, barcode);
            int k=pst.executeUpdate();
             sql="update stock set Qty=?,DateIn=? where pid=?";
             pst=con.prepareStatement(sql);
             pst.setInt(1, Integer.parseInt(txt_qty.getText()));
           
            pst.setDate(2, java.sql.Date.valueOf(datein_Picker.getValue()));
           
            pst.setInt(3, pid);
            int j=pst.executeUpdate();
             if(j==1 && k==1){
               
                alertDialog.display("Info", "Data Updated Successfully");
                prodName.setText("");
                prodBarcode.setText("");
                prodbprice.setText("");
                prodsprice.setText("");
                txt_qty.setText("");
                datein_Picker.setValue(LocalDate.now());
                
                 setCellTable();
        loadDataFromDatabase();
            }       
            
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void deletebtnpressed(ActionEvent event) {
        String sql="delete from producttwo where barcode=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1, prodBarcode.getText());
            int i=pst.executeUpdate();
            if(i==1){
                alertDialog.display("Info", "Data Deleted Successfully");
               
                prodName.setText("");
                prodBarcode.setText("");
                prodbprice.setText("");
                prodsprice.setText("");
                 loadDataFromDatabase();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchProduct(){
        txtsearch.setOnKeyReleased(e->{
            if(txtsearch.getText().equals("")){
                loadDataFromDatabase();
            }else{
                data.clear();
            
            String sql="select p.*, s.Qty,s.DateIn from producttwo p Inner join stock s on p.pid=s.pid and p.barcode like '%"+txtsearch.getText()+"%'"
                    +"UNION select p.*, s.Qty,s.DateIn from producttwo p Inner join stock s on p.pid=s.pid and p.pname like '%"+txtsearch.getText()+"%'"
                    +"UNION select p.*, s.Qty,s.DateIn from producttwo p Inner join stock s on p.pid=s.pid and p.bprice like '%"+txtsearch.getText()+"%'"
                    +"UNION select p.*, s.Qty,s.DateIn from producttwo p Inner join stock s on p.pid=s.pid and p.sprice like '%"+txtsearch.getText()+"%'";
            try{
                pst=con.prepareStatement(sql);
                
                rs=pst.executeQuery();
                while(rs.next()){
                    data.add(new ProductList(rs.getInt(1),rs.getString(2),rs.getString(3),""+rs.getDouble(4),""+rs.getDouble(5),""+rs.getInt(6),""+rs.getDate(7)));
                
                }
                TableView.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        });
    }
        
    
    private void setCellTable(){
        colbarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        bprice.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        sprice.setCellValueFactory(new PropertyValueFactory<>("sprice"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        coldatein.setCellValueFactory(new PropertyValueFactory<>("dateIn"));
    }
    
    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst=con.prepareStatement("select p.*, s.Qty,s.DateIn from producttwo p Inner join stock s on p.pid=s.pid;");
            rs=pst.executeQuery();
            while(rs.next()){
               data.add(new ProductList(rs.getInt(1),rs.getString(2),rs.getString(3),""+rs.getDouble(4),""+rs.getDouble(5),""+rs.getInt(6),""+rs.getDate(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableView.setItems(data);
    }
private void  setCellViewFromTableToTextField(){
    TableView.setOnMouseClicked(e -> {
       
        
           ProductList pl=   TableView.getItems().get(TableView.getSelectionModel().getSelectedIndex());
           prodBarcode.setText(pl.getBarcode());
           prodName.setText(pl.getName());
           
           prodbprice.setText(pl.getBprice());
           prodsprice.setText(pl.getSprice());
           txt_qty.setText(pl.getQty());
           datein_Picker.setValue(LocalDate.parse(pl.getDateIn()));
           pid = pl.getPid();

    
    
            });
    
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

    @FXML
    private void clearbtnpressed(ActionEvent event) {
        prodName.setText("");
                prodBarcode.setText("");
                prodbprice.setText("");
                prodsprice.setText("");
    }

   

   
    
}
