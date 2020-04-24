/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainCadastrarEmpresa;
import Main.MainTelaPrincipal;
import Model.DAO.EmpresaDAO;
import Model.Empresa;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class CadastrarEmpresaController implements Initializable {

    private static EmpresaDAO dao = new EmpresaDAO();

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    private ImageView imgEmpresa;

    private String caminhoFoto;

    @FXML
    void CadastrarE() {

        Empresa empresaCad = new Empresa();

        empresaCad.setNome(txtNome.getText());
        empresaCad.setCnpj(txtCNPJ.getText());
        empresaCad.setFoto(caminhoFoto);

        if (dao.addEmpresa(empresaCad)) {
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        }

    }

    @FXML
    void CancelarE() {

        MainCadastrarEmpresa.getJanela().close();
        MainTelaPrincipal.getJanela().show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imgEmpresa.setOnMouseClicked((MouseEvent e) -> {

            ExtensionFilter filtroIMG = new ExtensionFilter("Imagens", "*.jpg", "*.gif,", "*.png");

            FileChooser flMusic = new FileChooser();

            File foto = new File("");

            foto = flMusic.showOpenDialog(new Stage());

            if (foto != null) {

                imgEmpresa.setImage(new Image("file:///" + foto.getAbsolutePath()));
                caminhoFoto = foto.getAbsolutePath();
            }

        });

    }

}
