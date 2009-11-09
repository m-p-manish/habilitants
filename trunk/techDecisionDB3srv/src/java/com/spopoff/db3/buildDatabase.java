/*
Copyright Stéphane Georges Popoff, (mai - novembre 2009)

spopoff@rocketmail.com

Ce logiciel est un programme informatique servant à gérer des habilitations.

Ce logiciel est régi par la licence [CeCILL|CeCILL-B|CeCILL-C] soumise au droit français et
respectant les principes de diffusion des logiciels libres. Vous pouvez
utiliser, modifier et/ou redistribuer ce programme sous les conditions
de la licence [CeCILL|CeCILL-B|CeCILL-C] telle que diffusée par le CEA, le CNRS et l'INRIA
sur le site "http://www.cecill.info".

En contrepartie de l'accessibilité au code source et des droits de copie,
de modification et de redistribution accordés par cette licence, il n'est
offert aux utilisateurs qu'une garantie limitée.  Pour les mêmes raisons,
seule une responsabilité restreinte pèse sur l'auteur du programme,  le
titulaire des droits patrimoniaux et les concédants successifs.

A cet égard  l'attention de l'utilisateur est attirée sur les risques
associés au chargement,  à l'utilisation,  à la modification et/ou au
développement et à la reproduction du logiciel par l'utilisateur étant
donné sa spécificité de logiciel libre, qui peut le rendre complexe à
manipuler et qui le réserve donc à des développeurs et des professionnels
avertis possédant  des  connaissances  informatiques approfondies.  Les
utilisateurs sont donc invités à charger  et  tester  l'adéquation  du
logiciel à leurs besoins dans des conditions permettant d'assurer la
sécurité de leurs systèmes et ou de leurs données et, plus généralement,
à l'utiliser et l'exploiter dans les mêmes conditions de sécurité.

Le fait que vous puissiez accéder à cet en-tête signifie que vous avez
pris connaissance de la licence [CeCILL|CeCILL-B|CeCILL-C], et que vous en avez accepté les
termes.
 */
package com.spopoff.db3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * Cette classe permet la création des tables dans DB3 ou le vidage.
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
public class buildDatabase extends HttpServlet {
    private Connection connn = null;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        System.out.println("Arrivé dans la servlet buildDatabase !");
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            String message = "";
            Datamart dm = null;
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Datamart builder</title>");
            out.println("</head>");
            out.println("<body>");
            try{
                connn =  (Connection)getServletContext().getAttribute("connection");
                if(connn!=null){
                    message = "Connexion à la base de donnée "+connn.getClass()+" catalog="+connn.getCatalog();
                }else{
                    message = "Connexion vide";
                }
            }catch(Exception sq){
                message = sq.toString();
            }
            out.println(message+"<br/>");
            dm = new Datamart();
            out.println("jdbc="+getServletContext().getInitParameter("db.url")+"<br/>");
            try{
                dm.setJdbcUrl(getServletContext().getInitParameter("db.url"));
                message = "passage de l'url jdbc à Datamart";
            }catch(Exception err){
                message = err.toString();
            }
            //log léger
            dm.setLogMax(false);
            //log max
            //dm.setLogMax(true);
            out.println(message+"<br/>");
            out.println("user="+getServletContext().getInitParameter("db.user")+"<br/>");
            try{
                dm.setUserName(getServletContext().getInitParameter("db.user"));
                message = "passage de l'utilisateur";
            }catch(Exception err){
                message = err.toString();
            }
            out.println(message+"<br/>");
            out.println("passw="+getServletContext().getInitParameter("db.password")+"<br/>");
            try{
                dm.setPassword(getServletContext().getInitParameter("db.password"));
                message = "passage du mot de passe";
            }catch(Exception err){
                message = err.toString();
            }
            out.println(message+"<br/>");
            try{
                dm.initDb();
                message = "Création des tables";
            }catch(Exception err){
                message = err.toString();
            }
            out.println(message+"<br/>");
/*
            try{
                javax.naming.InitialContext ctx = new javax.naming.InitialContext();
                javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup("onDemandTechDB3");
                java.sql.Connection connection = ds.getConnection();
                message = "Ouverture / fermeture de la ressource jdcb onDemandTechDB3 sur catalogue:"+connection.getCatalog();
                connection.close();
                ctx.close();
            }catch(Exception err){
                message = err.toString();
            }
            out.println(message+"<br/>");
*/
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
            out.println(message+"<br/>");
            //test de la base ldap
            try {
                javax.naming.InitialContext initCtx = new javax.naming.InitialContext();
                javax.naming.directory.DirContext ctx = (javax.naming.directory.DirContext) initCtx.lookup("ldaptechDecision");

                SearchControls ctls = new SearchControls();
                ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

                String searchfilter = "(cn=spo)";
                NamingEnumeration answer = ctx.search("", searchfilter, ctls);

                if(answer.hasMore()){
                    SearchResult entry = (SearchResult) answer.next();
                    Attributes attrs = entry.getAttributes();
                    message = "Ouverture / fermeture de la ressource ldap/techDecision description="+attrs.get("description").get().toString();
                } else {
                    message="Erreur Ouverture / fermeture de la ressource ldap/techDecision pas trouvé cn=spo";
                }
                initCtx.close();
            } catch (javax.naming.NamingException e) {
                    message=e.toString();
            }
            out.println(message+"<br/>");
            out.println("<a href='./console'>lien vers console H2</a>");
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
        return "Short description";
    }// </editor-fold>

}
