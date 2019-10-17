/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Usuario;
/**
 *
 * @author thiagoa
 */
public class UsuarioDAO {
    
        public void create(Usuario c){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            try{
                stmt = con.prepareStatement("INSERT INTO usuario(nome,sobrenome,email,DataNasc,Senha,login,status) VALUES (?,?,?,?,?,?,?)");
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getSobrenome());
                stmt.setString(3, c.getEmail());
                stmt.setString(4, c.getDataNasc());
                stmt.setString(5, c.getSenha());
                stmt.setString(6, c.getLogin());
                stmt.setString(7, c.getStatus());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        }
        
    public List<Usuario> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
   
        List<Usuario> usuarios = new ArrayList<>();
        
        
        try {
            stmt = con.prepareStatement("SELECT idusuario, nome, sobrenome, email, datanasc, senha, login, CASE WHEN Status = 1 THEN 'Ativo'  ELSE 'Inativo' END as 'Status' FROM Usuario");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("Sobrenome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDataNasc(rs.getString("DataNasc"));
                usuario.setLogin(rs.getString("login"));
                usuario.setStatus(rs.getString("status"));
                usuarios.add(usuario);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usuarios;
    }
    public List<Usuario> readForName(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
   
        List<Usuario> usuarios = new ArrayList<>();
        
        
        try {
            stmt = con.prepareStatement("SELECT idusuario, nome, sobrenome, email, datanasc, senha, login, CASE WHEN Status = 1 THEN 'Ativo'  ELSE 'Inativo' END as 'Status' FROM Usuario WHERE nome like ? or sobrenome like ?");
            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + nome + "%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("Sobrenome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDataNasc(rs.getString("DataNasc"));
                usuario.setLogin(rs.getString("login"));
                usuario.setStatus(rs.getString("status"));
                usuarios.add(usuario);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usuarios;
    }
    
    
    public String nome(String login){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nome = "";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT idusuario, nome, sobrenome, email, datanasc, senha, login, CASE WHEN Status = 1 THEN 'Ativo'  ELSE 'Inativo' END as 'Status' FROM Usuario WHERE login = ? ORDER BY nome, sobrenome");
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            
            
            if(rs.next()){
                nome = rs.getString("nome").toString();
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return nome;
    }
                
        
    public void update(Usuario c){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            try{
                stmt = con.prepareStatement("UPDATE usuario SET nome = ?,sobrenome = ?,email = ?,DataNasc = ?,login = ? WHERE idusuario = ?");
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getSobrenome());
                stmt.setString(3, c.getEmail());
                stmt.setString(4, c.getDataNasc());
                stmt.setString(5, c.getLogin());
                stmt.setInt(6, c.getIdusuario());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        }
    
    public void updateSenha(String senha, String login){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            try{
                stmt = con.prepareStatement("UPDATE usuario SET senha = ? WHERE Login = ?");
                stmt.setString(1, senha);
                stmt.setString(2, login);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
    }
    
    public void updateSenhaPadrao(Usuario c){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            try{
                stmt = con.prepareStatement("UPDATE usuario SET senha = '123456' WHERE idusuario = ?");
                stmt.setInt(1, c.getIdusuario());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
    }

    public void delete(Usuario c){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            try{
                stmt = con.prepareStatement("DELETE FROM Usuario WHERE IDusuario = ?");
                stmt.setInt(1, c.getIdusuario());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
    }        
        
    public boolean ValidaLogin(String login){
        UsuarioDAO dao = new UsuarioDAO();
            for(Usuario u: dao.read()){
                if(u.getLogin().equals(login)){
                    return true;
                }
            }
            return false;

    }   
    
    public boolean ValidaLoginSenha(String login, String senha){
        UsuarioDAO dao = new UsuarioDAO();
            for(Usuario u: dao.read()){
                if(u.getLogin().equals(login)&&u.getSenha().equals(senha)){
                    return true;
                }
            }
            return false;

    }  
    
    public boolean checkLogin(String login, String senha){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
   
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Usuario WHERE Login = ? AND senha = ? AND Status = 1");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                check = true;
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return check;
        
    }
    
    
    public boolean verificarStatusLoginS(String login, String senha){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Usuario WHERE Login = ? AND senha = ? AND Status != 1");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                check = true;
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return check;
      
    }

    public boolean verificarStatusS(String login){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Usuario WHERE Login = ? AND Status != 1");
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if(rs.next()){
                check = true;
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return check;
      
    }

    public boolean verificarStatusA(String login){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Usuario WHERE Login = ? AND Status = 1");
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if(rs.next()){
                check = true;
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return check;
      
    } 

    public boolean verificaExisteLogin(String login, int i){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int contadorL = 0;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Usuario WHERE Login = ? AND idusuario != ?");
            stmt.setString(1, login);
            stmt.setInt(2, i);
            rs = stmt.executeQuery();
            while(rs.next()){
                contadorL++;
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        if(contadorL == 1){
            return true;
        }else{
            return false;
        }
    }    
    
    public boolean inativar(Usuario c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
   
        
        try{
            stmt = con.prepareStatement(" UPDATE Usuario SET Status = 0 WHERE Idusuario = ?");
            stmt.setInt(1, c.getIdusuario());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro inativo com sucesso!");
                
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inativar: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
        
        return check;
        
    }

    public boolean ativar(Usuario c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
   
        
        try{
            stmt = con.prepareStatement("UPDATE Usuario SET Status = 1 WHERE Idusuario = ?");
            stmt.setInt(1, c.getIdusuario());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro ativado com sucesso!");
                
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inativar: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
        
        return check;
        
    }    
    
        
}
