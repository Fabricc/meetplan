/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Implementazione;

import Modello.Organizzatore;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class OrganizzatoreMySqlImp implements Organizzatore {
    private String email,nome,cognome,pass;
    private Boolean grant;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public Boolean isGrant() {
        return grant;
    }

    @Override
    public void setGrant(Boolean grant) {
        this.grant = grant;
    }

        public Boolean setGrant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    }

