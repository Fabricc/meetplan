/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Implementazione.CentralDataLayerMySqlImpl;
import Implementazione.GestioneEventoDataLayerMySqlImp;
import Implementazione.GestioneVotazioneDataLayerMySqlImp;
import Implementazione.OpzioneMySqlImp;
import Implementazione.UtenteMySqlImp;
import Implementazione.VotoMySqlImp;
import Modello.CentralDataLayer;
import Modello.Evento;
import Modello.GestioneEventoDataLayer;
import Modello.GestioneVotazioneDataLayer;
import Modello.Opzione;
import Modello.Utente;
import Modello.Votante;
import Modello.Voto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabio Ricchiuti <Fab1234@hotmail.it>
 */
public class homepage_organizzatore extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet homepage_organizzatore</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet homepage_organizzatore at " + request.getContextPath() + "</h1>");
//            Calendar cal = Calendar.getInstance();
//            CentralDataLayer c = new CentralDataLayerMySqlImpl();
//            Connection conn = c.getConnection(getServletContext());
//            GestioneVotazioneDataLayer vot = c.getVotazioneDataLayer(conn);
//            GestioneEventoDataLayerMySqlImp ev = (GestioneEventoDataLayerMySqlImp) c.getEventoDataLayer(conn);
//            
//            
            
            // definisce un cookie che conterrà il risultato


// definisce un array di cookie per leggere quelli dell'utente


// indice per la gestione del ciclo
int indice = 0;

Cookie[] cookiesUtente = request.getCookies();
        

while (indice < cookiesUtente.length) {

    System.out.println(cookiesUtente.length);
  // esegue il ciclo fino a quando ci sono elementi in cookieUtente
  if (cookiesUtente[indice].getName().equals("prova")){System.out.println("ok"); break;}
  
  indice++; 
  
  // se trova un cookie con il nome che stiamo cercando esce dal ciclo
  
}// while 
            
            
//        Evento e;
//        e = ev.setEvento("fabio@email.it", "progetto musicale", "evento", null);
//        out.println("Evento da inserire");
//        out.println(e);
//        Utente tizio = new UtenteMySqlImp("tizio", "tizio", "tizio@email.it");
//        Utente cazio = new UtenteMySqlImp("caio", "caio", "caio@email.it");
//        Utente sempronio = new UtenteMySqlImp("sempronio", "sempronio", "sempronio@email.it");
//        ArrayList<Utente> l = new ArrayList();
//        
//        l.add(cazio);
//        l.add(tizio);
//        l.add(sempronio);
//        
//        Date a = new Date();
//        
//        ev.addOpzione(e, a, "Sala Centrale");
//        ev.addOpzione(e, a, "Sala Destra");
//        ev.addOpzione(e, a, "Sala Sinistra");
//        
//        vot.mettiInVotazione(e, l);
//        
//        out.println("Evento in votazione!");
        
//        Votante a_vot = vot.
//        
//        Voto v = new VotoMySqlImp(e.getId(),,);
            
            
//        out.println("prima");
//        out.println(e);
//        out.println("dopo");
            
            out.println("</body>");
            out.println("</html>");
            out.close();

    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
