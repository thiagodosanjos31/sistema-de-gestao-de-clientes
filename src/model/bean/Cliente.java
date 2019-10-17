
package model.bean;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;


public class Cliente {
    private int idCliente;
    private String Nome;
    private String Sobrenome;
    private String CPF;
    private String RG;
    private int Idade;
    private String Telefone;
    private String Celular;
    private String Email;
    private String Fax;
    private String RendaF;
    private String EstadoC;
    private String Raca;
    private String Genero;
    private String Orientacao;
    private String Filhos;
    private String GrauEscola;
    private String RendaI;
    private int lido;

    public int getLido() {
        return lido;
    }

    public void setLido(int lido) {
        this.lido = lido;
    }

    public String getRendaI() {
        return RendaI;
    }

    public void setRendaI(String RendaI) {
        this.RendaI = RendaI;
    }
    
    public int getIdade() {
        return Idade;
    }

    public void setIdade(int Idade) {
        this.Idade = Idade;
    }

    public String getGrauEscola() {
        return GrauEscola;
    }

    public void setGrauEscola(String GrauEscola) {
        this.GrauEscola = GrauEscola;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getRendaF() {
        return RendaF;
    }

    public void setRendaF(String RendaF) {
        this.RendaF = RendaF;
    }

    public String getEstadoC() {
        return EstadoC;
    }

    public void setEstadoC(String EstadoC) {
        this.EstadoC = EstadoC;
    }

    public String getRaca() {
        return Raca;
    }

    public void setRaca(String Raca) {
        this.Raca = Raca;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getOrientacao() {
        return Orientacao;
    }

    public void setOrientacao(String Orientacao) {
        this.Orientacao = Orientacao;
    }

    public String getFilhos() {
        return Filhos;
    }

    public void setFilhos(String Filhos) {
        this.Filhos = Filhos;
    }
    
}
