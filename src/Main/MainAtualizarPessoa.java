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
public class MainAtualizarPessoa extends Application{
    
    private static Stage janela;
    
    @Override
    public void start(Stage stage) throws Exception {
     
        Parent root = FXMLLoader.load(getClass().getResource("/View/AtualizarPessoa.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Cadastrar Usuario");
        stage.setScene(scene);
        stage.show();
        setJanela(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getJanela() {
        return janela;
    }

    public static void setJanela(Stage janela) {
        MainAtualizarPessoa.janela = janela;
    }
    
    
    
}
