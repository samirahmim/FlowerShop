
package flowershop;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class textfieldvalidation {
    public static boolean isValidEmail(TextField tf){
        boolean b=false;
        String pattern="\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        if(tf.getText().matches(pattern))
            b=true;
        return b;
    }
    /*public static boolean isValidEmail(TextField tf){
        boolean b=true;
        String msg=null;
        tf.getStyleClass().remove("error");
        if(!isValidEmail(tf)){
            b=false;
            msg=errorMsg;
            tf.getStyleClass().add("error");
        }
        
        return b;
    }*/
    
    
}
