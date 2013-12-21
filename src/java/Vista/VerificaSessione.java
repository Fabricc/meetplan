/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Implementazione.CentralDataLayerMySqlImpl;
import Modello.AutenticazioneDataLayer;
import Modello.CentralDataLayer;
import include.SecurityLayer;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alessandro
 */
public class VerificaSessione extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    SimpleDateFormat f = new SimpleDateFormat();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession s = SecurityLayer.checkSession(request);
        Writer out2 = response.getWriter();

        if (s == null) {
            CentralDataLayer a = new CentralDataLayerMySqlImpl();
            try {
                Connection conn = a.getConnection(getServletContext());
                AutenticazioneDataLayer auth = a.getAutenticazioneDataLayer(conn);
                Boolean bool_amm = auth.isAmministratore(request.getParameter("u"));
                out2.write("if tre ");
                if (bool_amm) {
                    out2.write("SONO AMMINISTRATORE");
                }
                out2.write("non SONO AMMINISTRATORE");

                out2.write(request.getParameter("u"));
                if (auth.Login(request, response)) {

                    out2.write("SONO fabio");
                }

            } catch (IOException e) {
                out2.write("ecc 1 ");
            }
            out2.close();
        }
        if (request.getParameter("logout") != null) {
            CentralDataLayer a = new CentralDataLayerMySqlImpl();
            
                Connection conn = a.getConnection(getServletContext());
                AutenticazioneDataLayer auth = a.getAutenticazioneDataLayer(conn);
                auth.Logout(request, response);
                out2.write("sono sloggato");
                response.sendRedirect("status");
            

        }
            //       out.close();

        //  } else {
        //      out2.write("no1");
        //      //action_logged(request, response);
    }
    //}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerificaSessione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerificaSessione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VerificaSessione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerificaSessione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
