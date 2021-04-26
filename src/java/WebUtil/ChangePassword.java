/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebUtil;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Trey
 */
public class ChangePassword extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         HttpSession session = request.getSession();
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String username = (String) session.getAttribute("username");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        try {
            conn = JdbcManager.getConnection();
            stmt = conn.createStatement();
            String sqlcmd = String.format("select * from users where username='%s' and password='%s'", username, oldpassword);
            rset = stmt.executeQuery(sqlcmd);
            if(rset.next()) {
                sqlcmd = String.format("update users set password='%s' where username='%s'", newpassword, username);
                stmt.executeUpdate(sqlcmd);
            }
            else {
                session.setAttribute("loggedin", false);
                session.setAttribute("username", null);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e){
           e.printStackTrace();
        } finally {
            JdbcManager.close(conn);
            JdbcManager.close(stmt);
            JdbcManager.close(rset);
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
