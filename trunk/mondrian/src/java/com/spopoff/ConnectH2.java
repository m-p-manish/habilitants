/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spopoff;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author spopoff
 */
public class ConnectH2 extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConnectH2</title>");  
            out.println("</head>");
            out.println("<body>");
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException ex) {
                out.println(ex.toString());
            }
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:h2:mem:techdecision;IFEXISTS=TRUE", "sa", "sa");
            } catch (SQLException ex) {
                out.println(ex.toString());
            }
            if(conn!=null){
                out.println("<h1>Connexion réussie</h1>");
            }
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException ex) {
                out.println(ex.toString());
            }
            out.println("<h4>test ressource jdbc</h4>");
            String message = null;
            try{
                javax.naming.InitialContext ctx = new javax.naming.InitialContext();
                javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbc/memTechDecision");
                java.sql.Connection connection = ds.getConnection();
                message = "Ouverture / fermeture de la ressource jdbc/memTechDecision sur catalogue:"+connection.getCatalog();
                connection.close();
                ctx.close();
            }catch(Exception err){
                message = err.toString();
            }
            out.println(message);
            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "test la connexion à la base H2";
    }// </editor-fold>

}
