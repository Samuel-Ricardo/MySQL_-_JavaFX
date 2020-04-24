/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainAtualizarPessoa;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class AtualizarPessoaController implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField pasSenha;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    private ImageView imgPessoa;

    private static Pessoa pessoaTB;

    private String selecionada;

    private FileChooser.ExtensionFilter filtroImagens = new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.gif,", "*.png");

    private PessoaDAO dao = new PessoaDAO();

    @FXML
    void atualizarP() {

        Pessoa pes = new Pessoa();

        pes.setId(pessoaTB.getId());
        pes.setLogin(txtNome.getText());
        pes.setSenha(pasSenha.getText());
        pes.setEmail(txtEmail.getText());
        pes.setFoto(selecionada);

        if (dao.update(pes)) {
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        }
    }

    @FXML
    void cancelarP() {

        MainAtualizarPessoa.getJanela().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciarComp();

        imgPessoa.setOnMouseClicked((t) -> {

            FileChooser fl = new FileChooser();
            fl.setSelectedExtensionFilter(filtroImagens);

            File foto = new File("");

            foto = fl.showOpenDialog(new Stage());

            imgPessoa.setImage(new Image("file:///" + foto.getAbsolutePath()));
            selecionada = foto.getAbsolutePath();
        });

    }

    private void iniciarComp() {

        txtNome.setText(pessoaTB.getLogin());
        pasSenha.setText(pessoaTB.getSenha());
        txtEmail.setText(pessoaTB.getEmail());
    }

    public static Pessoa getPessoaTB() {
        return pessoaTB;
    }

    public static void setPessoaTB(Pessoa pessoaTB) {
        AtualizarPessoaController.pessoaTB = pessoaTB;
    }

}
