/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainCadastrarPessoa;
import Main.MainLogin;
import Model.DAO.PessoaDAO;
import Model.Pessoa;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class CadastrarPessoaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField pasSenha;

    @FXML
    private Button btCadastrar;

    private PessoaDAO dao = new PessoaDAO();

    @FXML
    private Button btCancelar;

    @FXML
    private ImageView imgPessoa;

    private String selecionada;

    private FileChooser.ExtensionFilter filtroImagens = new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.gif,", "*.png");

    @FXML
    void cadastrarP() {

        cadastrar();

    }

    @FXML
    void cancelarP() {

        MainCadastrarPessoa.getJanela().close();
        MainLogin.getStage().show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        pasSenha.setOnKeyPressed((KeyEvent e) -> {

            if (e.getCode() == KeyCode.ENTER) {
                cadastrar();
            }
        });

        imgPessoa.setOnMouseClicked((t) -> {

            FileChooser flPessoa = new FileChooser();
            flPessoa.setSelectedExtensionFilter(filtroImagens);

            File foto = new File("");
            foto = flPessoa.showOpenDialog(new Stage());

            if (foto != null) {

                imgPessoa.setImage(new Image("file:///" + foto.getAbsolutePath()));
                selecionada = foto.getAbsolutePath();

            }

        });

    }

    public void cadastrar() {

        Pessoa pessoa = new Pessoa();

        pessoa.setLogin(txtNome.getText());
        pessoa.setEmail(txtEmail.getText());
        pessoa.setSenha(pasSenha.getText());
        pessoa.setFoto(selecionada);

        if (dao.cadPessoa(pessoa)) {
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso >.+");
        }
    }

}
