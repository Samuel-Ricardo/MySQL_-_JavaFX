/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import JDBC.ConnectionFactory;
import Model.Empresa;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class EmpresaDAO {

    Connection conexao;

    public EmpresaDAO() {

    }

    public boolean addEmpresa(Empresa empresa) {

        conectar();
        PreparedStatement comando = null;
        String sql = "INSERT INTO empresas (nome,cnpj,foto) VALUES(?,?,?)";

        try {

            comando = conexao.prepareStatement(sql);

            comando.setString(1, empresa.getNome());
            comando.setString(2, empresa.getCnpj());
            comando.setString(3, empresa.getFoto());

            comando.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(conexao, comando);
        }
    }

    public void delet(Empresa empresa) {

        conectar();
        PreparedStatement comando = null;
        String sql = "DELETE FROM empresas WHERE id = ?;";

        try {

            comando = conexao.prepareStatement(sql);

            comando.setInt(1, empresa.getId());

            comando.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, comando);
        }

    }

    public boolean update(Empresa empresa) {

        conectar();
        PreparedStatement comando = null;
        String sql = "UPDATE empresas SET nome = ?, cnpj = ?, foto = ? WHERE id = ?;";

        try {

            comando = conexao.prepareStatement(sql);

            comando.setString(1, empresa.getNome());
            comando.setString(2, empresa.getCnpj());
            comando.setString(3, empresa.getFoto());
            comando.setInt(4, empresa.getId());

            comando.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex);
            return false;
        }

    }

    public List<Empresa> selectAll() {

        conectar();
        PreparedStatement comando = null;
        ResultSet resultado = null;
        String sql = "SELECT * FROM empresas;";
        List<Empresa> empresas = new ArrayList<>();

        try {

            comando = conexao.prepareStatement(sql);

            resultado = comando.executeQuery();

            while (resultado.next()) {

                Empresa empresa = new Empresa();

                empresa.setId(resultado.getInt("id"));
                empresa.setNome(resultado.getString("nome"));
                empresa.setCnpj(resultado.getString("cnpj"));
                empresa.setFoto(resultado.getString("foto"));

                empresas.add(empresa);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar o Banco: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, comando, resultado);
        }

        return empresas;
    }

    private void conectar() {
        conexao = ConnectionFactory.getConection();
    }

}
