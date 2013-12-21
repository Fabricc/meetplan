/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package include;

import Modello.Utente;
import java.io.File;
import java.util.Random;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class Helpers {
    
    static public Boolean CheckEmailFormat(String e){
        
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        
        return e.matches(EMAIL_REGEX);
        
    }
    
    static public java.sql.Date DateJava_to_DateSql(java.util.Date javadate){
        
        java.sql.Date sqldate = new java.sql.Date(javadate.getTime());
        
        return sqldate;
    }
    
    static public java.util.Date DateSql_to_DateSql(java.sql.Date sqldate){
        
        java.util.Date javadate = new java.sql.Date(sqldate.getTime());
        
        return javadate;
    }
    
    static public File InviaFakeMail(Utente u, String ticket ){
        return null;
    }
    
    public static String randomString() {
        Random rand = new Random();
        StringBuffer tempStr = new StringBuffer();
        tempStr.append("");
        for (int i = 0; i < 16; i++) {
            int c = rand.nextInt(122 - 48) + 48;
            if ((c >= 58 && c <= 64) || (c >= 91 && c <= 96)) {
                i--;
                continue;
            }
            tempStr.append((char) c);

        }
        return tempStr.toString();
    }
    
}
