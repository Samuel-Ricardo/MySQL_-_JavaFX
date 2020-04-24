/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainAtualizarEmpresa;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class AtualizarEmpresaController implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private Button btAtualizar;

    @FXML
    private Button btCancelar;

    @FXML
    private ImageView imgEmpresa;

    private static Empresa empresaS;

    private EmpresaDAO dao = new EmpresaDAO();

    private String selecionada;

    @FXML
    void CancelarE() {

        MainAtualizarEmpresa.getJanela().close();

    }

    @FXML
    void atualizarE() {

        Empresa empresaAtl = new Empresa();

        empresaAtl.setCnpj(txtCNPJ.getText());
        empresaAtl.setFoto(selecionada);
        empresaAtl.setNome(txtNome.getText());
        empresaAtl.setId(empresaS.getId());

        dao.update(empresaAtl);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciar();

        imgEmpresa.setOnMouseClicked((t) -> {

            FileChooser fl = new FileChooser();

            File foto = new File("");

            foto = fl.showOpenDialog(new Stage());

            imgEmpresa.setImage(new Image("file:///" + foto.getAbsolutePath()));
            selecionada = foto.getAbsolutePath();

        });

    }

    private void iniciar() {

        txtCNPJ.setText(empresaS.getCnpj());
        txtNome.setText(empresaS.getNome());
        if (empresaS.getFoto() != null) {
            imgEmpresa.setImage(new Image("file:///" + empresaS.getFoto()));
        }
    }

    public static Empresa getEmpresaS() {
        return empresaS;
    }

    public static void setEmpresaS(Empresa empresaS) {
        AtualizarEmpresaController.empresaS = empresaS;
    }

}
