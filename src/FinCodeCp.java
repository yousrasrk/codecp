

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Amal
 */
public class FinCodeCp extends Application {
  
    
    @Override
    public void start(Stage stage) throws Exception {
      
        Parent root = FXMLLoader.load(getClass().getResource("Connect/Login.fxml"));
        
        Scene scene = new Scene(root);
        
         
        stage.setResizable(false);
      //  stage.setFullScreen(true);

        stage.setScene(scene);
        stage.show(); 
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Connect/Login.fxml"));
//        stage.setScene(new Scene((Pane) loader.load()));
//        LoginController controller = loader.<LoginController>getController();
//        controller.initData("amal");
//        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
