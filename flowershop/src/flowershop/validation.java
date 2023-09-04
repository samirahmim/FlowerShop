
package flowershop;

import javafx.scene.control.TextField;


public class validation {
    public static boolean isTextFieldNotEmpty(TextField tf){
      boolean b=false;
      if(tf.getText().length() != 0 || !tf.getText().isEmpty())
          b=true;
      return b;
    }
}
