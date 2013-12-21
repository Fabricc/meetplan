/*
 * status.java
 *
 * Questo esempio mostra come utilizzare le sessioni per autenticare un utente
 * 
 * This example shows how to use sessions to authenticate the user
 *
 */
package Vista;

import include.HTMLResult;
import include.SecurityLayer;
import java.io.*;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Ingegneria del Web
 * @version
 */
public class status extends HttpServlet {

    private void connessione(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {

        try {
            
           // HTMLResult result = new HTMLResult(getServletContext());
            //result.setTitle("lista utenti");

            //Class.forName("com.mysql.jdbc.Driver");
         //   Connection con = DriverManager.getConnection("jdbc:mysql://localhost/meetplanner", "root", "");
//            Statement stmt1 = con.createStatement();
//            ResultSet rs = stmt1.executeQuery("SELECT * FROM organizzatore");
//            result.appendToBody("<h1>lista utenti</h1>");
//            Writer messaggio = response.getWriter();
//            while (rs.next()) {
//                messaggio.write(rs.getString("Nome"));
//            }
//
//            messaggio.close();
//            rs.close();
//            stmt1.close();
//            con.close(); 
//            messaggio.close();
        } catch (Exception e) {
            //Writer write = response.getWriter();
            //write.write(e.getMessage());
        }
    }

    SimpleDateFormat f = new SimpleDateFormat();

    private void action_anonymous(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        HTMLResult result = new HTMLResult(getServletContext());

        result.setTitle("Welcome");
        result.appendToBody("<h1>Authentication required</h1>");
        result.appendToBody("<form method=\"post\" action=\"login\">");
        result.appendToBody("<p>Username: <input name=\"u\" type=\"text\"/></p>");
        result.appendToBody("<p>Password: <input name=\"p\" type=\"password\"/></p>");
        result.appendToBody("<p><input value=\"login\" name=\"login\" type=\"submit\"/></p>");
        result.appendToBody("</form>");
        result.activate(request, response);
    }

    private void action_logged(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //acquisiamo un riferimento alla sessione. Avendo gi eseguito un checksession siamo sicuri che sia attiva e valida
        HttpSession s = request.getSession(false);

        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Bentornato");
        result.appendToBody("<h1>Welcome back, " + (String) s.getAttribute("username") + "</h1>");
        result.appendToBody("<p>You IP address is: " + (String) s.getAttribute("ip") + "</p>");
        result.appendToBody("<p>Your connection staertd on: " + f.format(((Calendar) s.getAttribute("inizio-sessione")).getTime()) + "</p>");
        result.appendToBody("<form method=\"post\" action=\"VerificaSessione\">");
        result.appendToBody("<p><input value=\"logout\" name=\"logout\" type=\"submit\"/></p>");
        result.appendToBody("</form>");
        result.appendToBody("<p>Now you can access the <a href='secured'>secured page</a></p>");
        result.activate(request, response);
    }

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
        if (s == null) {
            connessione(request, response);
            action_anonymous(request, response);
        } else {
            connessione(request, response);
            action_logged(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(status.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(status.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(status.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(status.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
