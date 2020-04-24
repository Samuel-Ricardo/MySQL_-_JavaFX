/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import JDBC.ConnectionFactory;
import Model.Pessoa;
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
public class PessoaDAO {

    private Connection conexao;

    public boolean cadPessoa(Pessoa pessoa) {

        conexao = ConnectionFactory.getConection();
        PreparedStatement comando = null;
        String sql = "INSERT INTO pessoas (id,nome,email,senha,foto) VALUES(default,?,?,?,?);";

        try {

            comando = conexao.prepareStatement(sql);

            comando.setString(1, pessoa.getLogin());
            comando.setString(2, pessoa.getEmail());
            comando.setString(3, pessoa.getSenha());
            comando.setString(4, pessoa.getFoto());

            comando.execute();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(conexao, comando);
        }

    }

    public boolean update(Pessoa pessoa) {

        conexao = ConnectionFactory.getConection();
        PreparedStatement comando = null;
        String sql = "UPDATE pessoas SET nome = ?, email = ?, senha = ?, foto = ? WHERE id = ?;";

        try {

            comando = conexao.clientPrepareStatement(sql);

            comando.setString(1, pessoa.getLogin());
            comando.setString(2, pessoa.getEmail());
            comando.setString(3, pessoa.getSenha());
            comando.setString(4, pessoa.getFoto());
            comando.setInt(5, pessoa.getId());

            comando.execute();

            return true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(conexao, comando);
        }

    }

    public boolean delet(Pessoa pessoa) {

        conexao = ConnectionFactory.getConection();
        PreparedStatement comando = null;
        String sql = "DELETE FROM pessoas WHERE id = ?;";

        try {

            comando = conexao.prepareStatement(sql);

            comando.setInt(1, pessoa.getId());

            comando.execute();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(conexao, comando);
        }

    }

    public ArrayList<Pessoa> selectAll() {

        conexao = ConnectionFactory.getConection();
        PreparedStatement comando = null;
        ResultSet resultado = null;
        String sql = "SELECT * FROM pessoas";

        ArrayList<Pessoa> pessoas = new ArrayList();

        try {

            comando = conexao.prepareStatement(sql);

            resultado = comando.executeQuery();

            while (resultado.next()) {

                Pessoa p1 = new Pessoa();

                p1.setId(resultado.getInt("id"));
                p1.setLogin(resultado.getString("nome"));
                p1.setEmail(resultado.getString("email"));
                p1.setSenha(resultado.getString("senha"));
                p1.setFoto(resultado.getString("foto"));

                pessoas.add(p1);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar pesquisa: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, comando, resultado);
        }

        return pessoas;
    }

    public List<Pessoa> pesquisar(String pesquisa) {

        conexao = ConnectionFactory.getConection();
        PreparedStatement comando = null;
        ResultSet resultado = null;
        String sql = "SELECT * FROM pessoas WHERE nome LIKE ?;";

        List<Pessoa> pessoas = new ArrayList<>();

        try {

            comando = conexao.prepareStatement(sql);

            comando.setString(1, "%" + pesquisa + "%");

            resultado = comando.executeQuery();

            while (resultado.next()) {

                Pessoa pes = new Pessoa();

                pes.setId(resultado.getInt("id"));
                pes.setLogin(resultado.getString("nome"));
                pes.setEmail(resultado.getString("email"));
                pes.setSenha(resultado.getString("senha"));
                pes.setFoto(resultado.getString("foto"));

                pessoas.add(pes);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar pesquisa: " + ex);
        }
        return pessoas;
    }

}
