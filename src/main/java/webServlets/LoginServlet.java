package webServlets;

import JdbcDatasource.dao.PostDao;
import JdbcDatasource.dao.UserDao;
import JdbcDatasource.exceptions.DbFailException;
import JdbcDatasource.implementDao.LikeDaoImpl;
import JdbcDatasource.implementDao.PostDaoImpl;
import JdbcDatasource.implementDao.UserDaoImpl;
import bean.Like;
import bean.Post;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl ();
        response.setContentType ("text/html");
        PrintWriter out = response.getWriter ();

        String email = request.getParameter ("email");
        String password = request.getParameter ("password");

        if (email.isEmpty () || password.isEmpty ()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher ("/login.jsp");
            out.println ("<font color = red>Please fill all the fields</font>");
            dispatcher.include (request, response);
        } else {
            User user = userDao.gerUserByLogin (email, password);
            if (user.getUserPassword () != null || user.getUserEmail () != null) {
                try {
                    HttpSession session = request.getSession (true);
                    session.setAttribute ("user", user);
                    PostDao postDao = new PostDaoImpl ();
                    LikeDaoImpl likeDao = new LikeDaoImpl ();
                    List<Post> allPosts = postDao.getAllPosts ();
                    session.setAttribute ("allPosts", allPosts);
                    List<Like> allLikes = likeDao.getAllLike ();
                    session.setAttribute ("allLikes", allLikes);
                    RequestDispatcher dispatcher = request.getRequestDispatcher ("/welcome.jsp");
                    dispatcher.forward (request, response);
                } catch (DbFailException e) {
                    e.printStackTrace ();
                }

            } else {
                out.print ("<p style=\"color:red\"> Sorry, you email or password are wrong</p>");
                RequestDispatcher dispatcher = request.getRequestDispatcher ("/login.jsp");
                dispatcher.include (request, response);
            }
        }
        out.close ();
    }
}