/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sistema";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
           
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha de conexão com banco");
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }
    
    public static void testConnection(){
        try {
            Class.forName(DRIVER);
            DriverManager.getConnection(URL, USER, PASS);
            JOptionPane.showMessageDialog(null, "Conexao: " + URL + "\nUsuario: " + USER + "\nPassword: *******\nConexão realizada com sucesso!");
            
           
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha de conexão com banco");
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }
    
    public static void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }           
    }
    
    
        public static void closeConnection(Connection con, PreparedStatement stmt){

            closeConnection(con);
                try {
                    if(stmt != null){
                        stmt.close();
                    }
                }catch (SQLException ex) {
                    System.err.println("Erro:"+ex);
                }       
        }
        
        
        public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){

            closeConnection(con, stmt);
                try {
                    if(rs != null){
                        rs.close();
                    } 
                }catch (SQLException ex) {
                    System.err.println("Erro:"+ex);
                }       
           
        }
                
}

