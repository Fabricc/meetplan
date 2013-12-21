/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modello;

import java.util.List;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public interface GestioneVotazioneDataLayer {
    
    void addVoto(Voto v); //durante votazione
    Votante getVotanteByTicket(String ticket) throws TicketScadutoException; //durante votazione
    Evento getEventoByTicket(String ticket) throws TicketScadutoException; //durante votazione
    void mettiInVotazione(Evento e, List<Utente> utenti); //prima della votazione
    Evento chiudiVotazione(Evento e, Opzione o); //fine votazione

    public static class TicketScadutoException extends Exception {

        public TicketScadutoException() {
        }
    }
    
}
