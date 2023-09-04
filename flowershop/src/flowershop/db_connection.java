package flowershop;


import flowershop.productController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class db_connection {
    public static Connection flowershopConnection(){
        Connection con=null;
        String url="jdbc:sqlserver://localhost:1433;databaseName=prod;user=sa;password=p@ssword13";
        try {
            con=DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(productController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
