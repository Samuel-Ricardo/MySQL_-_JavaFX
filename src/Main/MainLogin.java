/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Samuel
 */
public class MainLogin extends Application{
    
    private static Stage janela;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage stage) throws Exception{
        
        Parent root = FXMLLoader.load(MainLogin.class.getResource("/View/Login.fxml"));//carrega o FXML
        
        Scene scene = new Scene(root);//Poe FXML numa Scena
        
        stage.setTitle("Login");
        stage.setScene(scene);//coloca Scena na Janlela
        stage.show();//mostra Janela
        
        setStage(stage);
        
    }

    public static Stage getStage() {
        return janela;
    }

    public static void setStage(Stage stage) {
        MainLogin.janela = stage;
    }
    
    
}
