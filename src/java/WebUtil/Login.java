package WebUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import WebUtil.JdbcManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        try {
            conn = JdbcManager.getConnection();
            stmt = conn.createStatement();
            String sqlcmd = String.format("select * from users where username like '%%%s%%' and password like '%%%s%%'", username, password);
            rset = stmt.executeQuery(sqlcmd);
            if(rset.next()) {
                session.setAttribute("loggedin", true);
                session.setAttribute("username", username);
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
