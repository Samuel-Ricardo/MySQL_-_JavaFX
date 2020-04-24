/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainCadastrarPessoa;
import Main.MainLogin;
import Main.MainTelaPrincipal;
import Model.DAO.PessoaDAO;
import Model.Pessoa;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class LoginController2 implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField passSenha;

    @FXML
    private Button btLogin;

    @FXML
    private Button btCadastrar;

    private Pessoa pessoa;

    // private MainLogin viewLogin = new MainLogin();
    private MainTelaPrincipal viewPrincipal = new MainTelaPrincipal();
    private MainCadastrarPessoa viewCadastro = new MainCadastrarPessoa();

    private PessoaDAO dao = new PessoaDAO();

    @FXML
    void cadastrar() {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("Cadastro");
        alerta.setContentText("Voce será redirecionado para área de cadastro");
        alerta.show();

        MainLogin.getStage().close();

        try {
            viewCadastro.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoginController2.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("pao");
    }

    @FXML
    void verificarLogin() {

        Pessoa pes = new Pessoa();

        System.out.println("1");
        pes.setLogin(txtNome.getText());
        System.out.println("2");
        pes.setSenha(passSenha.getText());
        System.out.println(pes.getSenha());

        if (logar(pes) == true) {

            MainLogin.getStage().close();

            try {
                viewPrincipal.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController2.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Nome ou Senha Incorretos ou Inexistentes");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtNome.setOnKeyPressed((KeyEvent e) -> {

            if (e.getCode() == KeyCode.ENTER) {
                verificarLogin();
            }
        });

        passSenha.setOnKeyPressed((KeyEvent e) -> {

            if (e.getCode() == KeyCode.ENTER) {
                verificarLogin();
            }

        });

    }

    public boolean logar(Pessoa pes) {

        int cont = 0;
        boolean existe = false;

        ArrayList<Pessoa> pessoasC = dao.selectAll();

        if (pessoasC.isEmpty() != true) {

            for (Pessoa p : pessoasC) {

                if (pes.getSenha().equals(pessoasC.get(cont).getSenha()) && pes.getLogin().equals(pessoasC.get(cont).getLogin())) {

                    existe = true;
                }
                cont++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Banco Vazio");
        }

        return existe;
    }

}
