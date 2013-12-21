/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modello;

import java.io.File;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public interface GestioneEventoDataLayer {

    List<Evento> getEventiByOrganizzatore(String organizzatore); //prima della votazione

    Evento setEvento(String organizzatore, String nome, String desc, File allegato); //prima della votazione

    Evento setEventoDefinito(String organizzatore, String nome, String desc, File allegato,
            Date inizio, Time durata, String sala); //prima della votazione

    Evento setEventoDefinito(Evento e, Date inizio, String sala); //fine della votazione

    Evento editEvento(Evento e);

    Boolean deleteEvento(Evento e);
    
    Set<Opzione> addOpzione(Evento e, Date inizio, String sala); //prima della votazione

    
}
