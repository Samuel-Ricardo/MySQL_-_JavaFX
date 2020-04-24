/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainCadastrarEmpresa;
import Main.MainCadastrarPessoa;
import Main.MainListarEmpresas;
import Main.MainListarPessoas;
import Main.MainTelaPrincipal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    void cadastraEmp(ActionEvent event) {

        if (MainCadastrarEmpresa.getJanela() == null) {
            MainCadastrarEmpresa viewEmp = new MainCadastrarEmpresa();

            try {

                viewEmp.start(new Stage());

            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MainCadastrarEmpresa.getJanela().show();
        }
    }

    @FXML
    void cadastrarP(ActionEvent event) {

        if (MainCadastrarPessoa.getJanela() == null) {
            MainCadastrarPessoa viewP = new MainCadastrarPessoa();

            try {

                viewP.start(new Stage());

            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            MainCadastrarPessoa.getJanela().show();
        }

    }

    @FXML
    void listarEmp(ActionEvent event) {

        MainListarEmpresas viewListarE = new MainListarEmpresas();

        try {

            viewListarE.start(new Stage());

        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void listarP(ActionEvent event) {

        if (MainListarPessoas.getJanela() == null) {

            MainListarPessoas viewListarP = new MainListarPessoas();

            try {

                viewListarP.start(new Stage());

            } catch (Exception ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            MainTelaPrincipal.getJanela().close();
            MainListarPessoas.getJanela().show();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
