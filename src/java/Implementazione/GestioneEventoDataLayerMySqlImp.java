/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementazione;

import Modello.Evento;
import Modello.GestioneEventoDataLayer;
import Modello.Opzione;
import include.Helpers;
import static include.Helpers.DateJava_to_DateSql;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class GestioneEventoDataLayerMySqlImp implements GestioneEventoDataLayer {

    Connection connection;
    PreparedStatement aEventoInVotazione, gEventiByOrganizzatore, gEvento,
            aEvento, eEvento, dEvento,gOpzioniByEvento;

    GestioneEventoDataLayerMySqlImp(Connection conn) throws SQLException {
        connection = conn;

        gEventiByOrganizzatore = connection.prepareStatement("SELECT IdEvento FROM evento WHERE OrganizzatoreEmail=?");
        gEvento = connection.prepareStatement("SELECT * FROM evento WHERE IdEvento=?");
        aEvento = connection.prepareStatement(""
                + "INSERT INTO evento "
                + "(`Nome`, `Descrizione`, `DataInizioDefinita`,"
                + " `Durata`, `OrganizzatoreEmail`, `SalaDefinita`,"
                + " `InVotazione`, `Allegato`) VALUES (?,?,?,?,?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
        eEvento = connection.prepareStatement(""
                + "UPDATE evento "
                + "SET `DataInizioDefinita` = ?, "
                + "`SalaDefinita` = ? "
                + "WHERE `IdEvento` = ?;");
        dEvento = connection.prepareStatement("DELETE FROM evento WHERE `IdEvento` = ?;");
        gOpzioniByEvento = connection.prepareStatement("SELECT * "
                + "FROM `opzione` WHERE `Evento`=?;");
        

    }

    @Override
    public List<Evento> getEventiByOrganizzatore(String email) {
        try {
            List<Evento> l = new ArrayList();
            Evento e;

            gEventiByOrganizzatore.setString(1, email);
            ResultSet rs = gEventiByOrganizzatore.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    e = this.getEventoFromMySql(rs.getInt("IdEvento"));
                    l.add(e);
                }
                rs.close();
                return l;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestioneEventoDataLayerMySqlImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Evento editEvento(Evento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean deleteEvento(Evento e) {
        try {
            EventoMySqlImp em = (EventoMySqlImp) e;
            dEvento.setInt(1, em.getId());
            dEvento.execute();

            return true;

        } catch (SQLException ex) {

        }

        return false;
    }

    @Override
    public Set<Opzione> addOpzione(Evento e, Date inizio, String sala) {

        OpzioneMySqlImp o = new OpzioneMySqlImp();

        o.setSala(sala);
        o.setDataInizio(Helpers.DateJava_to_DateSql(inizio));

        Set<Opzione> s = e.getOpzioni();
        
        if(s.isEmpty()) o.rendiFondamentale();
        
        s.add(o);

        return s;
    }
    

    @Override
    public Evento setEvento(String organizzatore, String nome, String desc, File allegato) {
        try {
        aEvento.setString(1, nome);
            aEvento.setString(2, desc);
            aEvento.setDate(3, null);
            aEvento.setTime(4, null);
            aEvento.setString(5, organizzatore);
            aEvento.setNull(6, 4); //4 è il codice che caratterizza il tipo int in sql
            aEvento.setBoolean(7, false);
            aEvento.setBlob(8, (Blob) allegato);
            aEvento.execute();
            
            

            ResultSet key = aEvento.getGeneratedKeys();
            key.next();

            EventoMySqlImp e = new EventoMySqlImp();

            e.setId(key.getInt(1));
            e.setNome(nome);
            e.setDescrizione(desc);
            e.setProprietario(organizzatore);
            e.setAllegato(allegato);

            key.close();
            return e;

        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        }

        return null;

    }

    @Override
    public Evento setEventoDefinito(String organizzatore, String nome, String desc, File allegato,
            Date inizio, Time durata, String sala) {
        try {
            aEvento.setString(1, nome);
            aEvento.setString(2, desc);
            aEvento.setDate(3, DateJava_to_DateSql(inizio));
            aEvento.setTime(4,durata);
            aEvento.setString(5, organizzatore);
            aEvento.setString(6, sala);
            aEvento.setBoolean(7, false);
            aEvento.setBlob(8, (Blob) allegato);
            aEvento.execute();

            ResultSet key = aEvento.getGeneratedKeys();
            key.next();

            EventoMySqlImp e = new EventoMySqlImp();

            e.setId(key.getInt(1));
            e.setNome(nome);
            e.setDescrizione(desc);
            e.setProprietario(organizzatore);
            e.setAllegato(allegato);
            e.setInizio(inizio);
            e.setDurata(durata);
            e.setLuogo(sala);
            e.setVotazione(Boolean.FALSE);

            key.close();
            return e;

        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        }

        return null;
    }

    @Override
    public Evento setEventoDefinito(Evento e, Date inizio, String sala) {

        try {

            java.sql.Date sqlinizio = new java.sql.Date(inizio.getTime());

            EventoMySqlImp em = (EventoMySqlImp) e;

            eEvento.setDate(1, sqlinizio);
            em.setInizio(inizio);
            eEvento.setString(2, sala);
            em.setLuogo(sala);

            eEvento.setInt(3, em.getId());

            eEvento.executeUpdate();

            return em;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;

    }

    public Evento getEventoFromMySql(int i) {
        try {

            gEvento.setInt(1, i);
            ResultSet rs = this.gEvento.executeQuery();

            if (rs != null) {

                rs.next();
                EventoMySqlImp e = new EventoMySqlImp();
                e.setId(i);
                e.setProprietario(rs.getString("OrganizzatoreEmail"));
                e.setNome(rs.getString("Nome"));
                e.setDescrizione(rs.getString("Descrizione"));
                e.setAllegato((File) rs.getBlob("Allegato"));
                e.setVotazione(rs.getBoolean("InVotazione"));
                e.setInizio(rs.getDate("DataInizioDefinita"));
                e.setDurata(rs.getTime("Durata"));
                e.setLuogo(rs.getString("SalaDefinita"));
                
                Set<Opzione> lista_opzioni = e.getOpzioni();
                
                gOpzioniByEvento.setInt(1, i);
                ResultSet ops = gOpzioniByEvento.executeQuery();
                
                OpzioneMySqlImp o;
                if(ops!=null){
                    while(ops.next()){
                        o = new OpzioneMySqlImp();
                        o.setId(ops.getInt("IdOpzione"));
                        o.setEvento(i);
                        o.setDataInizio(ops.getDate("DataInizio"));
                        o.setSala(ops.getString("Sala_Nome"));
                        o.setPunteggio(ops.getInt("Punteggio"));
                        
                        if(ops.getBoolean("Fondamentale")) o.rendiFondamentale();
                        
                        lista_opzioni.add(o);
                    }
                }

                return e;

            }

        } catch (SQLException ex) {
            Logger.getLogger(GestioneEventoDataLayerMySqlImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }
    

}
