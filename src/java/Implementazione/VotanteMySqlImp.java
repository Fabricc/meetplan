/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Implementazione;

import Modello.Utente;
import Modello.Votante;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
class VotanteMySqlImp extends UtenteMySqlImp implements Votante  {

    @Override
    public String toString() {
        return "VotanteMySqlImp{" + "Ticket=" + Ticket + ", IdEvento=" + IdEvento + ", commento=" + commento + 
               ""
                + " ,Nome="+super.getNome()+", "
                + "Cognome="+super.getCognome()+""
                + " ,Email="+super.getEmail()+'}';
    }
    
    private String Ticket;
    private int IdEvento;
    private String commento;
    
    /**
     * 
     * @param Ticket ticket
     * @param IdEvento evento
     * @param Nome nome
     * @param Cognome cognome
     * @param Email email
     */
    public VotanteMySqlImp(String Ticket, int IdEvento, String Nome,
            String Cognome, String Email) {
        super(Nome, Cognome, Email);
        this.Ticket = Ticket;
        this.IdEvento = IdEvento;
    }

    @Override
    public String getTicket() {
        return Ticket;
    }

    @Override
    public int getIdEvento() {
        return IdEvento;
    }

    @Override
    public String getCommento() {
        return commento;
    }
    
    }
