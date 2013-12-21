/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Implementazione;

import Modello.AutenticazioneDataLayer;
import Modello.CentralDataLayer;
import Modello.GestioneEventoDataLayer;
import Modello.GestioneVotazioneDataLayer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.sql.DataSource;


public class CentralDataLayerMySqlImpl implements CentralDataLayer {

    
        @Override
        public Connection getConnection(ServletContext c)   {
        DataSource ds = (DataSource) c.getAttribute("datasource");
            try {            
                return ds.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CentralDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
            
    }

    @Override
    public AutenticazioneDataLayer getAutenticazioneDataLayer(Connection con) {
            try {
                return new AutenticazioneDataLayerMySqlImpl(con); //To change body of generated methods, choose Tools | Templates.
            } catch (SQLException ex) {
                Logger.getLogger(CentralDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            return null;
            
    }

    @Override
    public GestioneEventoDataLayer getEventoDataLayer(Connection c) {
            try {
                return new GestioneEventoDataLayerMySqlImp(c);
            } catch (SQLException ex) {
                Logger.getLogger(CentralDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        
    }

    @Override
    public GestioneVotazioneDataLayer getVotazioneDataLayer(Connection c) {
    try {
                return new GestioneVotazioneDataLayerMySqlImp(c);
            } catch (SQLException ex) {
                Logger.getLogger(CentralDataLayerMySqlImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
   }