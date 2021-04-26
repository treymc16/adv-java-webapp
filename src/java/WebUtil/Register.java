package WebUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import WebUtil.JdbcManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//insert into users values(DEFAULT, 'test', 'test', 'test', 'test');

public class Register extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        try {
            conn = JdbcManager.getConnection();
            stmt = conn.createStatement();
            String sqlcmd = String.format("insert into users values(DEFAULT, '%s', '%s', '%s', '%s')", lastname, firstname, username, password);
            int result = stmt.executeUpdate(sqlcmd);

            if(result == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedin", true);
                session.setAttribute("username", username);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
                dispatcher.forward(request, response);
            }
            else {
                HttpSession session = request.getSession();
                session.setAttribute("loggedin", false);
                session.setAttribute("username", null);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
                dispatcher.forward(request, response);
            }

        } catch  (Exception e) {
            e.printStackTrace();
        }
        finally {
            JdbcManager.close(conn);
            JdbcManager.close(stmt);
            JdbcManager.close(rset);
        }

    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
