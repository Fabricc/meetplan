/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modello;

import java.io.File;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public interface Evento {

    public int getId();

    public String getNome();

    public String getDescrizione();

    public String getProprietario();

    public Boolean inVotazione();

    public Date getDataInizio();
    
    public Time getDurata();

    public File getAllegato();

    public String getLuogo();
    
    public Set<Opzione> getOpzioni();

}
