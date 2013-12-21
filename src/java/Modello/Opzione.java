/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modello;

import java.util.Date;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public interface Opzione {
    
    public int getId();
    
    public int getEvento();
    
    public String getSala();
    
    public Date getDataInizio();
    
    public int getPunteggio();
    
    public Boolean isFondamentale();
    
    public void rendiFondamentale();
    
}
