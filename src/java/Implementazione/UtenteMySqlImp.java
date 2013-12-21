/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Implementazione;

import Modello.Utente;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class UtenteMySqlImp implements Utente {
    
    private String Nome,Cognome,Email;

    public UtenteMySqlImp(String Nome, String Cognome, String Email) {
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.Email = Email;
    }

    @Override
    public String getNome() {
        return Nome;
    }

    @Override
    public String getCognome() {
        return Cognome;
    }

    @Override
    public String getEmail() {
        return Email;
    }
    
    }
