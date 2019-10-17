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
import model.bean.Cliente;


/**
 *
 * @author Thiago
 */
public class ClienteDAO {
        public void create(Cliente p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            try {
                stmt = con.prepareStatement("INSERT INTO cliente(nome,sobrenome,CPF,RG,Idade,Telefone,Celular,Email,Fax,RendaF,EstadoC,Raca,Genero,Orientacao,Filhos,GrauEscola,RendaI,Lido) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, p.getNome());
                stmt.setString(2, p.getSobrenome());
                stmt.setString(3, p.getCPF());
                stmt.setString(4, p.getRG());
                stmt.setInt(5, p.getIdade());
                stmt.setString(6, p.getTelefone());
                stmt.setString(7, p.getCelular());
                stmt.setString(8, p.getEmail());
                stmt.setString(9, p.getFax());
                stmt.setString(10, p.getRendaF());
                stmt.setString(11, p.getEstadoC());
                stmt.setString(12, p.getRaca());
                stmt.setString(13, p.getGenero());
                stmt.setString(14, p.getOrientacao());
                stmt.setString(15, p.getFilhos());
                stmt.setString(16, p.getGrauEscola());
                stmt.setString(17, p.getRendaI());
                stmt.setInt(18, 0);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar" + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        }
        
    public List<Cliente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT idcliente, nome, sobrenome, rg, cpf, Idade, telefone, celular, email, fax, rendaf, estadoc, raca, genero, orientacao, case when filhos = 1 then 'Possui' else 'Não possui' end as 'Filhos', grauEscola, RendaI FROM Cliente ORDER BY Nome, Sobrenome");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setIdade(rs.getInt("Idade"));
                cliente.setRG(rs.getString("RG")); 
                cliente.setCPF(rs.getString("CPF"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setFax(rs.getString("Fax"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setRendaF(rs.getString("RendaF"));
                cliente.setEstadoC(rs.getString("EstadoC"));
                cliente.setGenero(rs.getString("Genero"));
                cliente.setRaca(rs.getString("Raca"));
                cliente.setGrauEscola(rs.getString("GrauEscola"));
                cliente.setFilhos(rs.getString("Filhos"));
                cliente.setRendaI(rs.getString("RendaI"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    
    public List<Cliente> readForName(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT idcliente, nome, sobrenome, rg, cpf, Idade, telefone, celular, email, fax, rendaf, estadoc, raca, genero, orientacao, case when filhos = 1 then 'Possui' else 'Não possui' end as 'Filhos', grauEscola, rendaI FROM Cliente WHERE Nome like ? OR Sobrenome like ? ORDER BY Nome, Sobrenome");
                stmt.setString(1,"%" + nome + "%");
                stmt.setString(2,"%" + nome + "%");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setIdade(rs.getInt("Idade"));
                cliente.setRG(rs.getString("RG")); 
                cliente.setCPF(rs.getString("CPF"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setFax(rs.getString("Fax"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setRendaF(rs.getString("RendaF"));
                cliente.setEstadoC(rs.getString("EstadoC"));
                cliente.setGenero(rs.getString("Genero"));
                cliente.setRaca(rs.getString("Raca"));
                cliente.setGrauEscola(rs.getString("GrauEscola"));
                cliente.setFilhos(rs.getString("Filhos"));
                cliente.setRendaI(rs.getString("RendaI"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
        
    public void update(Cliente c){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            try{
                stmt = con.prepareStatement("UPDATE cliente SET nome = ?,sobrenome = ?,Idade = ?, CPF = ?, RG = ?, Telefone = ?, Celular = ?, Fax = ?, email = ?, RendaF = ?,EstadoC = ?, Genero = ?, Raca = ?, Filhos = ?, GrauEscola = ?, RendaI = ? WHERE idcliente = ?");
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getSobrenome());
                stmt.setString(9, c.getEmail());
                stmt.setInt(3, c.getIdade());
                stmt.setString(4, c.getCPF());
                stmt.setString(5, c.getRG());
                stmt.setString(6, c.getTelefone());
                stmt.setString(7, c.getCelular());
                stmt.setString(8, c.getFax());
                stmt.setString(10, c.getRendaF());
                stmt.setString(11, c.getEstadoC());
                stmt.setString(12, c.getGenero());
                stmt.setString(13, c.getRaca());
                stmt.setString(14, c.getFilhos());
                stmt.setString(15, c.getGrauEscola());
                stmt.setString(16, c.getRendaI());
                stmt.setInt(17, c.getIdCliente());
                
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        }
    public void delete(Cliente c){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            try{
                stmt = con.prepareStatement("DELETE FROM Cliente WHERE idCliente = ?");
                stmt.setInt(1, c.getIdCliente());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
                
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
    }
    
    
    /* CONSULTAS PARA O RELATÓRIO */
    public List<Cliente> readForRelatory1(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory2(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory3(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Orientacao LIKE '%Hetero%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory4(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory5(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente,nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE  Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND  EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory6(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%') AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory7(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory8(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE  Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory9(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1  LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory10(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND    Raca LIKE '%Branco%'  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory11(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND     Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory12(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')   AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory13(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));                
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory14(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%' AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory15(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory16(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory17(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory18(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'   AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%'  AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory19(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND  (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory20(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'    AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%' AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory21(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory22(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory23(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory24(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    public List<Cliente> readForRelatory25(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%' AND Lido != 1  LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    
        public List<Cliente> readForRelatory26(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
            public List<Cliente> readForRelatory27(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }

        public List<Cliente> readForRelatory28(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    
            public List<Cliente> readForRelatory29(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%' AND Lido != 1  LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }

    public List<Cliente> readForRelatory30(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   Filhos like '%0%'  AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }

    public List<Cliente> readForRelatory31(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND  GrauEscola LIKE '%sup%compl%' AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    
    public List<Cliente> readForRelatory32(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND  GrauEscola LIKE '%sup%compl%' AND   Filhos like '%0%' AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
    
    public List<Cliente> readForRelatory33(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  (Idade > 21 AND Idade < 30) AND Lido != 1 LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
        
            public List<Cliente> readForRelatory34(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  Filhos like '%0%' AND Lido != 1  LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }
                public List<Cliente> readForRelatory35(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
            try {
                stmt = con.prepareStatement("SELECT   idCliente, nome,      sobrenome,     telefone,      celular,      email  FROM   Cliente  WHERE   Genero LIKE '%feminino%'  AND   Orientacao LIKE '%Hetero%'  AND   Raca LIKE '%Branco%'  AND   (RendaI LIKE '%5 a 6%' OR RendaI LIKE '%6 a 7%' OR RendaI LIKE '%7 a 8%' OR RendaI LIKE '%8 a 9%' OR RendaI LIKE '%Mais%')  AND   (RendaF LIKE '%7 a 8%' OR RendaF LIKE '%8 a 9%' OR RendaF LIKE '%9 a 10%' OR RendaF LIKE '%10 a 11%' OR RendaF LIKE '%Mais%')  AND   EstadoC LIKE '%Divorciado%' AND  GrauEscola LIKE '%sup%compl%' AND Lido != 1  LIMIT 0, 1000");
                rs = stmt.executeQuery();
                while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setCelular(rs.getString("Celular"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setIdCliente(rs.getInt("idCliente"));
                clientes.add(cliente);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
            }
        return clientes;
    }

    public void updateLido(int idCliente){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("UPDATE cliente SET Lido = 1 WHERE idcliente = ?");
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();

        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
    public void updateDesler(int idCliente){    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("UPDATE cliente SET Lido = 0 WHERE idcliente = ?");
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

}