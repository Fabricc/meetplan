/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementazione;

import Modello.Evento;
import Modello.Opzione;
import java.io.File;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class EventoMySqlImp implements Evento {

    int id;
    String nome, descrizione, proprietario;
    Boolean votazione;
    Date inizio;
    Time durata;
    File allegato;
    String luogo;
    Set<Opzione> lista_opzioni;
    
    EventoMySqlImp(){
        lista_opzioni = new <Opzione>HashSet();
    }
    
    @Override
    public Set<Opzione> getOpzioni(){
        return lista_opzioni;
    }
    
    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public Boolean inVotazione() {
        return votazione;
    }

    public void setVotazione(Boolean votazione) {
        this.votazione = votazione;
    }

    @Override
    public Date getDataInizio() {
        return inizio;
    }

    public void setInizio(Date inizio) {
        this.inizio = inizio;
    }

    @Override
    public Time getDurata() {
        return durata;
    }

    public void setDurata(Time durata) {
        this.durata = durata;
    }

    @Override
    public File getAllegato() {
        return allegato;
    }

    public void setAllegato(File allegato) {
        this.allegato = allegato;
    }

    @Override
    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }
    
    @Override
    public String toString() {
        String res = "EventoMySqlImp{" + "id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", proprietario=" + proprietario + ", votazione=" + votazione + ", inizio=" + inizio + ", durata=" + durata + ", allegato=" + allegato + ", luogo=" + luogo + '}';
        res += "Opzioni relative "+lista_opzioni;
        return res;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    

}
