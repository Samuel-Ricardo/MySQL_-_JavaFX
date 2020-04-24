/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.MainAtualizarPessoa;
import Model.DAO.PessoaDAO;
import Model.Pessoa;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class ListarPessoasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Pessoa> tbPessoas;

    @FXML
    private TableColumn<Pessoa, Integer> clmID;

    @FXML
    private TableColumn<Pessoa, String> clmNome;

    @FXML
    private TableColumn<Pessoa, String> clmEmail;

    @FXML
    private ImageView imgPessoa;

    @FXML
    private TextField txtPesquisa;

    private ObservableList<Pessoa> pessoas;

    private static PessoaDAO dao = new PessoaDAO();

    private static Pessoa selecionada = new Pessoa();

    private ExtensionFilter pdf = new ExtensionFilter("PDF", "*.pdf");

    @FXML
    void AlterarP() {

        AtualizarPessoaController.setPessoaTB(selecionada);

        MainAtualizarPessoa atualiza = new MainAtualizarPessoa();

        try {
            atualiza.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ListarPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void atualisarP() {

        iniciarTB();

    }

    @FXML
    void pesquisar() {

        dao.pesquisar(txtPesquisa.getText());

    }

    @FXML
    void deletarP() {

        selecionada = tbPessoas.getSelectionModel().getSelectedItem();

        if (dao.delet(selecionada)) {
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        }
        iniciarTB();

    }

    @FXML
    void gerarPDF() {

        int cont = 0;

        String localEscolhido;
        FileChooser flImage = new FileChooser();

        List<Pessoa> pessoas = dao.selectAll();

        Document doc = new Document();

        flImage.getExtensionFilters().add(pdf);

        File fPDF = new File("");

        fPDF = flImage.showSaveDialog(new Stage());

        localEscolhido = fPDF.getAbsolutePath();

        if (fPDF != null) {
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(localEscolhido + ".pdf"));

                JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

            } catch (FileNotFoundException | DocumentException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Criar PDF: " + ex);
            }

            doc.open();

            doc.setPageSize(PageSize.A4);
            doc.addTitle("Descriçao de Empresas");
            doc.addCreationDate();
            doc.addCreator("Samuel Ricardo");
            doc.addKeywords("https://github.com/Samuel-Ricardo");
            doc.addAuthor("Samuel");

            for (Pessoa p : pessoas) {

                try {
                    System.out.println("p");

                    // Image perfil = Image.g(pessoas.get(cont).getFoto()); 
                    doc.add(new Paragraph("==================================================================="));
                    doc.add(new Paragraph(" "));
                    doc.add(new Paragraph(" "));
                    doc.add(new Paragraph("+ID: " + pessoas.get(cont).getId()));
                    doc.add(new Paragraph("+Nome: " + pessoas.get(cont).getLogin()));
                    doc.add(new Paragraph("+Email: " + pessoas.get(cont).getEmail()));
                    doc.add(new Paragraph("+Caminho da Foto: " + pessoas.get(cont).getFoto()));
                    doc.add(new Paragraph("+Senha: " + pessoas.get(cont).getSenha()));
                    doc.add(new Paragraph(" "));
                    doc.add(new Paragraph("==================================================================="));
                    doc.add(new Paragraph(" "));

                } catch (DocumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao escrever PDF: " + ex);
                }
                cont++;
            }

            doc.close();

        } else {
            JOptionPane.showMessageDialog(null, "arquivo vazio");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarTB();

        tbPessoas.getSelectionModel().selectedItemProperty().addListener((o) -> {

            selecionada = tbPessoas.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                imgPessoa.setImage(new Image("file:///" + selecionada.getFoto()));
            }

            iniciarTB();

        });
    }

    private void iniciarTB() {

        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNome.setCellValueFactory(new PropertyValueFactory<>("login"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        pessoas = FXCollections.observableArrayList(dao.selectAll());

        tbPessoas.setItems(pessoas);

    }

    public static Pessoa getSelecionada() {
        return selecionada;
    }

    public static void setSelecionada(Pessoa selecionada) {
        ListarPessoasController.selecionada = selecionada;
    }

}
