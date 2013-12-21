/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public interface AutenticazioneDataLayer {

    Boolean Login(HttpServletRequest in, HttpServletResponse out);

    Boolean Logout(HttpServletRequest in, HttpServletResponse out);

    Boolean isAmministratore(String email);

}
