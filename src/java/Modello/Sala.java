/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modello;

import java.util.Set;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public interface Sala {
    
    Boolean addAttrezzatura(Attrezzatura a);
    Boolean deleteAttrezzatura(Attrezzatura a);
    Set<Attrezzatura> getAttrezzature();
    
}
