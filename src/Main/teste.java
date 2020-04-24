/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import JDBC.ConnectionFactory;
import com.mysql.jdbc.Connection;

/**
 *
 * @author Samuel
 */
public class teste {
    
    public static void main(String[] args) {
        Connection conexao = ConnectionFactory.getConection();
        System.out.println("conectado");
        ConnectionFactory.closeConnection(conexao);
        System.out.println("desconectado");    
        
    }
    
}
