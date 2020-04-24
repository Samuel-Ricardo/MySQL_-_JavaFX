/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public  class  ConnectionFactory {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3308/pessoa";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConection() {

        try {

            Class.forName(DRIVER);

            return (Connection) DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel realizar conexao com o banco: " + ex);
            throw new RuntimeException("Nao foi possivel realizar conexao com o banco: " + ex);
        }

    }

    public static void closeConnection(Connection conexao) {

        try {

            if (conexao != null) {
                conexao.close();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel encerrar conexao com o banco: " + ex);
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement comando) {

        closeConnection(conexao);

        try {
            if (comando != null) {

                comando.close();
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Nao foi possivel encerrar conexao com o banco: " + ex);
        }

    }

    public static void closeConnection(Connection conexao, PreparedStatement comando, ResultSet resultado) {

        closeConnection(conexao, comando);

        try {
            if (resultado != null) {
                resultado.close();
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Nao foi possivel encerrar conexao com o banco: " + ex);
        }

    }

}
