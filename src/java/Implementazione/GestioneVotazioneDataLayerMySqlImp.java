/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementazione;

import Modello.Evento;
import Modello.GestioneVotazioneDataLayer;
import Modello.Opzione;
import Modello.Utente;
import Modello.Votante;
import Modello.Voto;
import include.Helpers;
import static include.Helpers.DateJava_to_DateSql;
import static include.Helpers.randomString;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class GestioneVotazioneDataLayerMySqlImp implements GestioneVotazioneDataLayer {

    Connection connection;
    PreparedStatement cambia_stato_votazione, aOpzione, dOpzione,
            aVotante, aVoto, sommavoto, gVotanteByTicket, gEventoByTicket,
            definisciEvento, deleteOpzioni, deleteVotanti, deleteVoti,
            votazionefatta;

    GestioneVotazioneDataLayerMySqlImp(Connection conn) throws SQLException {
        connection = conn;
        cambia_stato_votazione = connection.prepareStatement(""
                + "UPDATE evento "
                + "SET `InVotazione` = ? "
                + "WHERE `IdEvento` = ?;");
        aOpzione = connection.prepareStatement("INSERT INTO opzione "
                + "(`Evento`, `DataInizio`, "
                + " `Fondamentale`,`Sala_Nome`) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        aVotante = connection.prepareStatement("INSERT INTO votante "
                + "(`Evento`, `Utente_Email`, `Ticket`) VALUES (?,?,?)");
        aVoto = connection.prepareStatement("INSERT INTO voto "
                + "(`Votante_Evento`, `Votante_Utente_Email`,"
                + " `Opzione_IdOpzione`) VALUES (?,?,?)");
        sommavoto = connection.prepareStatement("UPDATE `opzione` "
                + "SET `Punteggio`=`Punteggio`+1 WHERE `IdOpzione`=?");
        gEventoByTicket = connection.prepareStatement("SELECT "
                + "IdEvento, Nome, Descrizione, Allegato, InVotazione, OrganizzatoreEmail"
                + " FROM evento AS e INNER JOIN votante AS v"
                + " ON e.IdEvento=v.Evento"
                + " WHERE Ticket = ?");
        gVotanteByTicket = connection.prepareStatement("SELECT * FROM votante"
                + " INNER JOIN utente "
                + "ON Utente_Email = Email "
                + "WHERE Ticket = ?");
        definisciEvento = connection.prepareStatement(""
                + "UPDATE evento "
                + "SET `DataInizioDefinita` = ?, "
                + "`SalaDefinita` = ? "
                + "WHERE `IdEvento` = ?;");
        deleteVotanti = connection.prepareStatement("DELETE FROM votante"
                + " WHERE `Evento` = ?;");
        deleteOpzioni = connection.prepareStatement("DELETE FROM opzione"
                + " WHERE `Evento` = ?;");
        deleteVoti = connection.prepareStatement("DELETE FROM voto"
                + " WHERE `Votante_Evento` = ?;");
        votazionefatta = connection.prepareStatement("UPDATE `votante` "
                + "SET `VotazioneFatta` = '1' WHERE `Evento` =? AND `Utente_Email` = ?;");

    }

    @Override
    public void addVoto(Voto v) {
        try {
            
            aVoto.setInt(1, v.getIdEvento());
            aVoto.setString(2, v.getEmail());
            aVoto.setInt(3, v.getOpzione());
            aVoto.executeUpdate();
            
            votazionefatta.setInt(1, v.getIdEvento());
            votazionefatta.setString(2, v.getEmail());
            votazionefatta.executeUpdate();

            sommavoto.setInt(1, v.getOpzione());
            sommavoto.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestioneVotazioneDataLayerMySqlImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void mettiInVotazione(Evento e, List<Utente> utenti) {

        Set<Opzione> opzioni = e.getOpzioni();

        try {
            cambia_stato_votazione.setBoolean(1, true);
            cambia_stato_votazione.setInt(2, e.getId());
            cambia_stato_votazione.executeUpdate();
            
            OpzioneMySqlImp o_sql;
            ResultSet key;

            for (Opzione o : opzioni) {
                aOpzione.setInt(1, e.getId());
                aOpzione.setDate(2, DateJava_to_DateSql(o.getDataInizio()));
                aOpzione.setBoolean(3, o.isFondamentale());
                aOpzione.setString(4, o.getSala());
                aOpzione.execute();

                key = aOpzione.getGeneratedKeys();
                key.next();
                o_sql = (OpzioneMySqlImp) o;
                o_sql.setId(key.getInt(1));
                key.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestioneEventoDataLayerMySqlImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.invita_utenti(e, utenti);

    }
    
//    eEvento = connection.prepareStatement(""
//                + "UPDATE evento "
//                + "SET `DataInizioDefinita` = ?, "
//                + "`SalaDefinita` = ? "
//                + "WHERE `IdEvento` = ?;");

    @Override
    public Evento chiudiVotazione(Evento e, Opzione o) {
        try {
            Date inizio = Helpers.DateJava_to_DateSql(o.getDataInizio());
            String sala = o.getSala();
            
            definisciEvento.setDate(1, inizio);
            definisciEvento.setString(2, sala);
            definisciEvento.setInt(3, e.getId());
            definisciEvento.executeUpdate();
            
            EventoMySqlImp es = (EventoMySqlImp)e;
            es.setInizio(inizio);
            es.setLuogo(sala);
            
            
            deleteVoti.setInt(1, e.getId());
            deleteVotanti.setInt(1, e.getId());
            deleteOpzioni.setInt(1, e.getId());
            
            deleteVoti.executeUpdate();
            deleteVotanti.executeUpdate();
            deleteOpzioni.executeUpdate();
            
            return e;
            
        } catch (SQLException ex) {
            Logger.getLogger(GestioneVotazioneDataLayerMySqlImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public void invita_utenti(Evento e, List<Utente> utenti) {

        int id_evento = e.getId();
        String ticket;

        for (Utente u : utenti) {
            try {
                ticket = randomString();
                aVotante.setInt(1, id_evento);
                aVotante.setString(2, u.getEmail());
                aVotante.setString(3, ticket);
                aVotante.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(GestioneEventoDataLayerMySqlImp.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public Votante getVotanteByTicket(String ticket) throws TicketScadutoException {
        try {
            gVotanteByTicket.setString(1, ticket);
            ResultSet rs;
            rs = gVotanteByTicket.executeQuery();

            if (rs != null) {
                rs.next();

                VotanteMySqlImp v = new VotanteMySqlImp(
                        rs.getString("ticket"),
                        rs.getInt("Evento"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Email")
                );

                rs.close();
                return v;

            } else {
                throw new TicketScadutoException();
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestioneVotazioneDataLayerMySqlImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

//    gEventoByTicket = connection.prepareStatement("SELECT "
//                + "Nome, Descrizione, Allegato, InVotazione, OrganizzatoreEmail"
//                + " FROM evento INNER JOIN votante"
//                + "ON IdEvento=Evento"
//                + " WHERE Ticket = ?;");
    @Override
    public Evento getEventoByTicket(String ticket) throws TicketScadutoException {
        try {
            gEventoByTicket.setString(1, ticket);
            ResultSet rs;

            rs = gEventoByTicket.executeQuery();
            rs.next();

            if (rs.getBoolean("InVotazione")) {

                EventoMySqlImp e = new EventoMySqlImp();
                e.setId(rs.getInt("IdEvento"));
                e.setNome(rs.getString("Nome"));
                e.setDescrizione(rs.getString("Descrizione"));
                e.setProprietario(rs.getString("OrganizzatoreEmail"));
                e.setVotazione(Boolean.TRUE);
                rs.close();
                return e;

            } else {
                throw new TicketScadutoException();
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestioneVotazioneDataLayerMySqlImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

}
