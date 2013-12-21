/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Implementazione;

import Modello.Opzione;
import java.sql.Date;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class OpzioneMySqlImp implements Opzione {

    private int id;
    private String Sala;
    private Date DataInizio;
    private int Punteggio = 0;
    private int Evento;
    private Boolean Fondamentale = false;
    
    @Override
    public int getEvento(){
        return Evento;
    }
    
    public void setEvento(int Evento){
        this.Evento=Evento;
    }
    
    
    
    @Override
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id=id;
    }
   
    

    @Override
    public String getSala() {
        return Sala;
    }

    public void setSala(String Sala) {
        this.Sala = Sala;
    }

    @Override
    public Date getDataInizio() {
        return DataInizio;
    }

    public void setDataInizio(Date DataInizio) {
        this.DataInizio = DataInizio;
    }

    @Override
    public int getPunteggio() {
        return Punteggio;
    }

    
    public void setPunteggio(int p) {
        this.Punteggio=p;
    }
    
    @Override
    public Boolean isFondamentale(){
        return Fondamentale;
    }
    
    @Override
    public void rendiFondamentale(){
        Fondamentale=true;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OpzioneMySqlImp other = (OpzioneMySqlImp) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OpzioneMySqlImp{" + "id=" + id + ", Sala=" + Sala + ", DataInizio=" + DataInizio + ", Punteggio=" + Punteggio + ", Evento=" + Evento + ", Fondamentale=" + Fondamentale + '}';
    }

    
    
    
    
    
}
