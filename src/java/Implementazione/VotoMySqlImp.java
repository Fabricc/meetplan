/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Implementazione;

import Modello.Voto;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class VotoMySqlImp implements Voto {

    

    public VotoMySqlImp(int Evento, String Email, int Opzione) {
        this.Evento = Evento;
        this.Email = Email;
        this.Opzione = Opzione;
    }
    
    private int Evento;
    private String Email;
    private int Opzione;
    
    

    @Override
    public int getIdEvento() {
        return Evento;
    }

    @Override
    public String getEmail() {
        return Email;
    }

    @Override
    public int getOpzione() {
        return Opzione;
    }
    
    @Override
    public String toString() {
        return "VotoMySqlImp{" + "Evento=" + Evento + ", Email=" + Email + ", Opzione=" + Opzione + '}';
    }
}
