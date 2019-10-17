
package Excel;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.swing.JOptionPane;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.bean.Cliente;
import model.dao.ClienteDAO;

public class GerarRelatorio {
private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat arial;
    private String inputArquivo;

    // Exemplo de Como criar uma planilha com JXL no Java
    public void setOutputFile(String inputArquivo) {
        this.inputArquivo = inputArquivo;
    }

    // Método responsável por fazer a escrita, a inserção dos dados na planilha
    public void insere() throws IOException, WriteException {
        // Cria um novo arquivo
        File arquivo = new File(inputArquivo+".xls");
        int i = 1;
        if(arquivo.exists()){
            int j = JOptionPane.showConfirmDialog(null ,"O arquivo " + inputArquivo + " já existe. Deseja sobreescrever?", "Confirmação de relatório",JOptionPane.YES_NO_OPTION);
            if(j ==JOptionPane.NO_OPTION){
                while(arquivo.exists()){
                arquivo = new File(inputArquivo + " ("+ i +")"+".xls");
                i++;
                }
            }
        }
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("pt", "BR"));

        WritableWorkbook workbook = Workbook.createWorkbook(arquivo, wbSettings);
        // Define um nome para a planilha
        workbook.createSheet("Relatório", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        criaLabel(excelSheet);
        defineConteudoRelatorio(excelSheet);

        workbook.write();
        workbook.close();
    }

    // Método responsável pela definição das labels
    private void criaLabel(WritableSheet sheet)
    throws WriteException {
        // Cria o tipo de fonte como TIMES e tamanho
        WritableFont arial12pt = new WritableFont(WritableFont.ARIAL, 10);

        // Define o formato da célula
        arial = new WritableCellFormat(arial12pt);

        // Efetua a quebra automática das células
        arial.setWrap(true);

        // Cria a fonte em negrito com underlines
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false);
        //UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);

        // Efetua a quebra automática das células
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(arial);
        cv.setFormat(timesBoldUnderline);
        cv.setSize(2);

        // Escreve os cabeçalhos
        addCaption(sheet, 0, 0, "Nome");
        addCaption(sheet, 1, 0, "Sobrenome");
        addCaption(sheet, 2, 0, "E-mail");
        addCaption(sheet, 3, 0, "Telefone");
        addCaption(sheet, 4, 0, "Celular");
    }

    private void defineConteudo(WritableSheet sheet) throws WriteException,
    RowsExceededException {
    // Escreve alguns números
    for (int i = 1; i < 10; i++) {
        // Primeira coluna
        addNumero(sheet, 0, i, i + 10);
        // Segunda coluna
        addNumero(sheet, 1, i, i * i);
        // Terceira coluna
        addNumero(sheet, 2, i, 10 - i);
    }

    // Efetua a soma das colunas criadas anteriormente
    StringBuffer buf = new StringBuffer();
        buf.append("SUM(A2:A10)");
        Formula f = new Formula(0, 10, buf.toString());
        sheet.addCell(f);
        buf = new StringBuffer();
        buf.append("SUM(B2:B10)");
        f = new Formula(1, 10, buf.toString());
        sheet.addCell(f);
        buf = new StringBuffer();
        buf.append("SUM(C2:C10)");
        f = new Formula(2, 10, buf.toString());
        sheet.addCell(f);

    // Agora vamos inserir algum texto nas colunas
        for (int i = 12; i < 20; i++) {
        // Primeira coluna
        addLabel(sheet, 0, i, "JExcel " + i);
        // Segunda coluna
        addLabel(sheet, 1, i, "Tutorial");
        // Terceira coluna
        addLabel(sheet, 2, i, "Exemplo" + (10 - i));
        }
        
    }
    private void defineConteudoRelatorio(WritableSheet sheet) throws WriteException,
    RowsExceededException {
    // Escreve alguns números
    ClienteDAO udao = new ClienteDAO();
    int Linha = 1;
    for(Cliente u: udao.readForRelatory1()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
        
    }
    for(Cliente u: udao.readForRelatory2()){
            addLabel(sheet, 0, Linha, u.getNome());
            // Segunda coluna
            addLabel(sheet, 1, Linha, u.getSobrenome());
            // Terceira coluna
            addLabel(sheet, 2, Linha, u.getEmail());
            //Quarta Coluna
            addLabel(sheet, 3, Linha, u.getTelefone());
            //Quinta coluna
            addLabel(sheet, 4, Linha, u.getCelular());
            Linha++;
            udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory3()){
            // Primeira coluna
            addLabel(sheet, 0, Linha, u.getNome());
            // Segunda coluna
            addLabel(sheet, 1, Linha, u.getSobrenome());
            // Terceira coluna
            addLabel(sheet, 2, Linha, u.getEmail());
            //Quarta Coluna
            addLabel(sheet, 3, Linha, u.getTelefone());
            //Quinta coluna
            addLabel(sheet, 4, Linha, u.getCelular());
            Linha++;
            udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory4()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory5()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory6()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory7()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory8()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory9()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory10()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory11()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory12()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory13()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory14()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory15()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory16()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory17()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());        
    }
    for(Cliente u: udao.readForRelatory18()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory19()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory20()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory21()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory22()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory23()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory24()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory25()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory26()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory27()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory28()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory29()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory30()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory31()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory32()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory33()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory34()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    for(Cliente u: udao.readForRelatory35()){
        // Primeira coluna
        addLabel(sheet, 0, Linha, u.getNome());
        // Segunda coluna
        addLabel(sheet, 1, Linha, u.getSobrenome());
        // Terceira coluna
        addLabel(sheet, 2, Linha, u.getEmail());
        //Quarta Coluna
        addLabel(sheet, 3, Linha, u.getTelefone());
        //Quinta coluna
        addLabel(sheet, 4, Linha, u.getCelular());
        Linha++;
        udao.updateLido(u.getIdCliente());
    }
    
    for(Cliente u: udao.read()){
        udao.updateDesler(u.getIdCliente());
    }
    
    

/*    // Efetua a soma das colunas criadas anteriormente
    StringBuffer buf = new StringBuffer();
        buf.append("SUM(A2:A10)");
        Formula f = new Formula(0, 10, buf.toString());
        sheet.addCell(f);
        buf = new StringBuffer();
        buf.append("SUM(B2:B10)");
        f = new Formula(1, 10, buf.toString());
        sheet.addCell(f);
        buf = new StringBuffer();
        buf.append("SUM(C2:C10)");
        f = new Formula(2, 10, buf.toString());
        sheet.addCell(f);*/

    // Agora vamos inserir algum texto nas colunas

        
    }

    // Adiciona cabecalho
    private void addCaption(WritableSheet planilha, int coluna, int linha, String s)
        throws RowsExceededException, WriteException {
        Label label;
        label = new Label(coluna, linha, s, timesBoldUnderline);
        planilha.addCell(label);
    }

    private void addNumero(WritableSheet planilha, int coluna, int linha,
        Integer integer) throws WriteException, RowsExceededException {
        jxl.write.Number numero;
        numero = new jxl.write.Number(coluna, linha, integer, arial);
        planilha.addCell(numero);
    }

    private void addLabel(WritableSheet planilha, int coluna, int linha, String s)
        throws WriteException, RowsExceededException {
        Label label;
        label = new Label(coluna, linha, s, arial);
        planilha.addCell(label);
    }   
}
