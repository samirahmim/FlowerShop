
package flowershop;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class alertDialog {
    public static void display(String title,String message){
        Stage window=new Stage();
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMaxHeight(100);
        
        Label label=new Label();
        label.setText(message);
        Button btnOk=new Button("OK");
        btnOk.setOnAction(e-> window.close());
        
        VBox layout = new VBox(5);
        layout.getChildren().addAll(label, btnOk);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
