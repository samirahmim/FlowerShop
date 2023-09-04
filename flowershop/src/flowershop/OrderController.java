/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class OrderController implements Initializable {
private Connection con = null;
    private PreparedStatement pst = null;
     private ResultSet rs=null;
    private ObservableList<orderlist> data;
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
    @FXML
    private TextField txtprodname;
    @FXML
    private TextField txtqty;
    @FXML
    private TableView<orderlist> TableOrder;
    @FXML
    private TableColumn<orderlist, Integer> colorderNo;
    @FXML
    private TableColumn<orderlist, Integer> colprodid;
     @FXML
    private TableColumn<?, String> colbarcode;
    @FXML
    private TableColumn<orderlist, String> colprodname;
    @FXML
    private TableColumn<orderlist, Double> colprice;
    @FXML
    private TableColumn<orderlist, Integer> colqty;
    @FXML
    private TableColumn<orderlist, Double> colamnt;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txtbarcode;
    int no=0;
    int prodId;
    String barcode;
    String ProdName;
    double sellprice;
    int qty=0;
    double amount=0.0;
    @FXML
    private TextField txtinvoice;
    @FXML
    private DatePicker orderdate;
    @FXML
    private Label lblgrandtotal;
    private double grandtotal=0.0;
    @FXML
    private Button btnprintinvoice;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       con = db_connection.flowershopConnection(); 
       txtinvoice.setText(autoOrderId());
       colorderNo.setCellValueFactory(new PropertyValueFactory<>("no"));
       colprodid.setCellValueFactory(new PropertyValueFactory<>("productId"));
       colbarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
       colprodname.setCellValueFactory(new PropertyValueFactory<>("productName"));
       colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
       colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
       colamnt.setCellValueFactory(new PropertyValueFactory<>("amount"));
        data=FXCollections.observableArrayList(); 
    }    

    @FXML
    private void savebtnclick(ActionEvent event) {
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
    private void handleorder(ActionEvent event) {
        int qty=Integer.parseInt(txtqty.getText());
        if(qty!=0){
            amount=sellprice*qty;
            grandtotal+=amount;
            for(orderlist item : data){
                if(item.getProductId()==prodId){
                    int tableqty=item.getQty()+qty;
                    double tableamnt=item.getAmount()+amount;
                    item.setQty(tableqty);
                    item.setAmount(tableamnt);
                    lblgrandtotal.setText(""+grandtotal);
                    TableOrder.getItems().set(TableOrder.getItems().indexOf(item),item);
                    clearText();
                    return;
                }
            }
            data.add(new orderlist(++no,prodId,barcode,ProdName,sellprice,qty,amount));
            TableOrder.setItems(data);
            lblgrandtotal.setText(""+grandtotal);
            clearText();
        }
    }
    private void clearText(){
        txtbarcode.clear();
        txtbarcode.requestFocus();
        txtprodname.clear();
        txtprice.clear();
        txtqty.clear();
    }
    private String autoOrderId(){
        String orderId="inv00000";
    try {
        
        pst=con.prepareStatement("select max(OrderId) from orderProduct");
        rs=pst.executeQuery();
        if(rs.next()){
            int n=Integer.parseInt(orderId.substring(3))+1;
            int x=String.valueOf(n).length();
            orderId=orderId.substring(0,8-x)+String.valueOf(n);
        }
        rs.close();
    } catch (SQLException ex) {
        Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return orderId; 
    }

    @FXML
    private void handlescanproduct(KeyEvent event) throws SQLException {
        pst=con.prepareStatement("select * from producttwo where barcode=?");
        pst.setString(1, txtbarcode.getText());
        rs=pst.executeQuery();
        if(rs.next()){
           
            
            prodId=rs.getInt(1);
            barcode=rs.getString(2);
            ProdName=rs.getString(3);
            sellprice=rs.getDouble(5);
            txtprodname.setText(ProdName);
            txtprice.setText(""+sellprice);
            txtqty.requestFocus();
        }
        rs.close();
    }

    @FXML
    private void handleprintinvoice(ActionEvent event) {
        String sql="insert into orderProduct(OrderId,OrderDate) values(?,?)";
        try{
            pst=con.prepareStatement(sql);
            pst.setString(1, txtinvoice.getText());
            pst.setDate(2, java.sql.Date.valueOf(orderdate.getValue()));
        } catch (SQLException ex) {
        Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
