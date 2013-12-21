/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Implementazione;

import Modello.Attrezzatura;
import Modello.Sala;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class SalaMySqlImp implements Sala {

    public SalaMySqlImp(int id, int capienza, String nome, String descrizione, List<AttrezzaturaMySqlImp> attrezzi) {
        this.id = id;
        this.capienza = capienza;
        this.nome = nome;
        this.descrizione = descrizione;
        this.attrezzi = attrezzi;
    }
    int id,capienza;
    String nome, descrizione;
    List<AttrezzaturaMySqlImp> attrezzi;
    
    

    @Override
    public Boolean addAttrezzatura(Attrezzatura a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean deleteAttrezzatura(Attrezzatura a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Attrezzatura> getAttrezzature() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
