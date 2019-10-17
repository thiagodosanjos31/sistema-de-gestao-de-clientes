/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class Valida {
    
    public static boolean isValidateEmail(String email) {  
    Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");   
    Matcher matcher = pattern.matcher(email);   
    return matcher.find();   
    }
    
    public static boolean isValidData(String date, String format) {         
	SimpleDateFormat dateFormat = new SimpleDateFormat(format);     
	dateFormat.setLenient(false);                                   
	try {                                                           
		dateFormat.parse(date);                                     
		return true;                                                
	} catch (ParseException e) {                                    
		return false;                                               
	}                                                               
    }
    
    public static boolean validaDiaMesAno(String date) {    
    try{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataVerificada = LocalDate.parse(date, dtf);
        LocalDate hoje = LocalDate.now();
        return dataVerificada.compareTo(hoje) <= 0;
    }catch (DateTimeParseException e) {                                    
		return false;                                               
    }                         
    
/*       
        if(Integer.valueOf(date.substring(0,2)) > 31 && Integer.parseInt(date.substring(3,5)) < 0){
            JOptionPane.showMessageDialog(null, "Dia errado" + date.substring(0,2) );
            return false;
       }else if(Integer.parseInt(date.substring(3,5)) > 12 && Integer.parseInt(date.substring(3,5)) < 0){
            return false;
        }else if(Integer.parseInt(date.substring(6,10)) > 2018 && Integer.parseInt(date.substring(6,10)) < 0){
            return false;
        }else{
            return true;
        }*/
    }
    
    
    public static boolean eNumero(String t) {
        if (t.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {  
          return true;
        } else {  
          return false;
        }  
    }
    
}
