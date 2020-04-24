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
public class MainAtualizarEmpresa extends Application{

    private static Stage janela;
    
    @Override
    public void start(Stage stage) throws Exception {
      
        Parent root = FXMLLoader.load(getClass().getResource("/View/AtualizarEmpresa.fxml"));
    
        Scene scene = new Scene(root);
        
        stage.setTitle("Atualizar Empresa");
        stage.setScene(scene);
        stage.show();
        
        setJanela(janela);
    }

    public static Stage getJanela() {
        return janela;
    }

    public static void setJanela(Stage janela) {
        MainAtualizarEmpresa.janela = janela;
    }
    
    
    
}
