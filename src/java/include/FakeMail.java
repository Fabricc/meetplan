/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package include;

import java.util.Date;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class FakeMail {
    
    /*
    From:<inviti@meetplan.com>
    To: <[email_utente]>
    Subject: Invito all'evento [nome_evento]
    
    [Logo azienda]
    Salve [nome_utente],
    sei stato invitato all'evento "[nome_evento]",
    puoi visionare i dettagli di questo evento e 
    decidere le combinazioni data/luogo più comode per te
    al link:
    
    http://meetplan/invite?ticket=[ticket]
    
    A seguito in una votazione ti faremo sapere
    quale sarà la combinazione scelta.
    
    Saluti,
    Lo staff di Meetplan
    */
    static public void FakeEmailInvite(String email_utente, String nome_utente,
            String nome_evento, String tickets){
        
    }
    
     /*
    From:<inviti@meetplan.com>
    To: <[email_utente]>
    Subject: Evento "[Nome_evento]" confermato
    
    [Logo azienda]
    Una delle tue combinazioni è stata scelta.
    L'evento "[nome_evento]" si terrà
    il [inizio] a [luogo]
    
    Ti aspettiamo,
    Lo staff di Meetplan
    */
    
    static public void FakeEmailConfirm(String email_utente, String nome_utente,
            String nome_evento, Date inizio, String luogo){
    }
    
    /*
    From:<inviti@meetplan.com>
    To: <[email_utente]>
    Subject: Evento "[Nome_evento]" confermato
    
    [Logo azienda]
    Purtroppo non sono state scelte nessune delle tue combinazioni.
    L'evento "[nome_evento]" si terrà
    il [inizio] a [luogo]
    
    Speriamo comunque che potrai venire,
    Lo staff di Meetplan
    */
    static public void FakeEmailRefuse(String email_utente, String nome_utente,
            String nome_evento, Date inizio, String luogo){
    }
    
    
}
