/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementazione;

import Modello.AutenticazioneDataLayer;
import include.SecurityLayer;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
public class AutenticazioneDataLayerMySqlImpl implements AutenticazioneDataLayer {

    Connection connection;
    PreparedStatement gcredenziali;
    PreparedStatement gamministratore;

    AutenticazioneDataLayerMySqlImpl(Connection con) throws SQLException {
        connection = con;
        gcredenziali = connection.prepareStatement("SELECT email,password FROM organizzatore ");
        gamministratore = connection.prepareStatement("SELECT Amministratore FROM organizzatore WHERE email=? ");
    }

    @Override
    public Boolean isAmministratore(String email) {
        ResultSet rs = null;

        try {
            gamministratore.setString(1, email);
            rs = gamministratore.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AutenticazioneDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                if (rs.next()) {
                    if (rs.getBoolean("Amministratore")) {
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutenticazioneDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean Login(HttpServletRequest in, HttpServletResponse out) {
        Writer out2 = null;
        try {
            out2 = out.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(AutenticazioneDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        try {
            rs = gcredenziali.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AutenticazioneDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString("email").equals(in.getParameter("u")) && rs.getString("password").equals(in.getParameter("p"))) {

                        SecurityLayer.createSession(in, in.getParameter("u"), 1);
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutenticazioneDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out2.write("ritorno falso");
        } catch (IOException ex) {
            Logger.getLogger(AutenticazioneDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean Logout(HttpServletRequest in, HttpServletResponse out) {
        try {
            SecurityLayer.disposeSession(in);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
