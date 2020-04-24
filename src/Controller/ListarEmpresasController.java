/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainAtualizarEmpresa;
import Model.DAO.EmpresaDAO;
import Model.Empresa;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class ListarEmpresasController implements Initializable {

    @FXML
    private TableView<Empresa> tbEmpresas;

    @FXML
    private TableColumn<Empresa, Integer> clmID;

    @FXML
    private TableColumn<Empresa, String> clmNome;

    @FXML
    private TableColumn<Empresa, String> clmCNPJ;

    @FXML
    private ImageView imgEmpresa;

    private ObservableList<Empresa> empresas;

    private Empresa selecionado = new Empresa();

    private EmpresaDAO dao = new EmpresaDAO();

    private ExtensionFilter pdf = new FileChooser.ExtensionFilter("PDF", "*.pdf");

    @FXML
    void atualizarE(ActionEvent event) {

        inicializarTB();

    }

    @FXML
    void deletarE(ActionEvent event) {

        dao.delet(selecionado);

    }

    @FXML
    void gerarPDF(ActionEvent event) {

        int cont = 0;

        List<Empresa> empresas = dao.selectAll();

        Document doc = new Document();

        FileChooser flRelatório = new FileChooser();
        flRelatório.getExtensionFilters().add(pdf);
        File fPDF = new File("");
        fPDF = flRelatório.showSaveDialog(new Stage());

        String localSelecionado = fPDF.getAbsolutePath();

        if (fPDF != null) {

            try {
                PdfWriter.getInstance(doc, new FileOutputStream(localSelecionado + ".pdf"));

                doc.open();

                doc.setPageSize(PageSize.A4);
                doc.addTitle("Descriçao de Empresas");
                doc.addCreationDate();
                doc.addCreator("Samuel Ricardo");
                doc.addKeywords("https://github.com/Samuel-Ricardo");
                doc.addAuthor("Samuel");

                for (Empresa e : empresas) {

                    // Image perfil = Image.g(pessoas.get(cont).getFoto()); 
                    doc.add(new Paragraph("==================================================================="));
                    doc.add(new Paragraph(" "));
                    doc.add(new Paragraph(" "));
                    doc.add(new Paragraph("+ID: " + empresas.get(cont).getId()));
                    doc.add(new Paragraph("+Nome: " + empresas.get(cont).getNome()));
                    doc.add(new Paragraph("+CNPJ: " + empresas.get(cont).getCnpj()));
                    doc.add(new Paragraph("+Caminho da Foto: " + empresas.get(cont).getFoto()));
                    doc.add(new Paragraph(" "));
                    doc.add(new Paragraph("==================================================================="));
                    doc.add(new Paragraph(" "));

                    cont++;
                }
                doc.close();
                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

            } catch (FileNotFoundException | DocumentException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Criar PDF: " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "arquivo vazio");
        }

    }

    @FXML
    void alterarE(ActionEvent event) {

        MainAtualizarEmpresa viewAtualizarE = new MainAtualizarEmpresa();

        try {

            AtualizarEmpresaController.setEmpresaS(selecionado);
            viewAtualizarE.start(new Stage());

        } catch (Exception ex) {
            Logger.getLogger(ListarEmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTB();

        tbEmpresas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {

                selecionado = (Empresa) newValue;

                if (selecionado != null) {
                    System.out.println(selecionado.getFoto());
                    imgEmpresa.setImage(new Image("file:///" + selecionado.getFoto()));
                }
                inicializarTB();

            }
        });
    }

    private void inicializarTB() {

        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clmCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));

        empresas = FXCollections.observableArrayList(dao.selectAll());

        tbEmpresas.setItems(empresas);

    }

}
