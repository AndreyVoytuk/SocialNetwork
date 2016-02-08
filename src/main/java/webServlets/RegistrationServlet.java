package webServlets;

import JdbcDatasource.dao.UserDao;
import JdbcDatasource.exceptions.DbFailException;
import JdbcDatasource.implementDao.UserDaoImpl;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/RegistrationServlet")
public class RegistrationServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl ();
        response.setContentType ("text/html");
        PrintWriter out = response.getWriter ();

        String username = request.getParameter ("username");
        String email = request.getParameter ("email");
        String password = request.getParameter ("password");

        if (username.isEmpty () || email.isEmpty () || password.isEmpty ()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher ("/registration.jsp");
            out.println ("<font color = red>Please fill all the fields</font>");
            dispatcher.include (request, response);
        } else {
            User user = new User ();
            user.setUserName (username);
            user.setUserEmail (email);
            user.setUserPassword (password);
            user.setUserStatus ("active");
            try {
                userDao.addUser (user);
                HttpSession session = request.getSession ();
                session.setAttribute ("user", user);
                response.sendRedirect ("/welcome.jsp");
            } catch (DbFailException e) {
                e.printStackTrace ();
                RequestDispatcher dispatcher = request.getRequestDispatcher ("/registration.jsp");
                out.println ("<font color = red>Problem!!!Try again registration, please! </font>");
                dispatcher.include (request, response);
            }
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
