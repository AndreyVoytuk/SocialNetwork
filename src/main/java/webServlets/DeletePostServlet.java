package webServlets;

import JdbcDatasource.dao.PostDao;
import JdbcDatasource.dao.UserDao;
import JdbcDatasource.exceptions.DbFailException;
import JdbcDatasource.implementDao.PostDaoImpl;
import JdbcDatasource.implementDao.UserDaoImpl;
import bean.Post;
import bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DeletePostServlet", urlPatterns = "/deletePost")
public class DeletePostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt (request.getParameter ("postId"));

        HttpSession session = request.getSession (true);
        User user = (User) session.getAttribute ("user");
        PostDao postDao = new PostDaoImpl ();
        try {
            postDao.removePost (id);
            List<Post> allPosts = postDao.getAllPosts (user.getUserId ());
            session.setAttribute ("allPosts", allPosts);
            response.sendRedirect ("welcome.jsp");
        } catch (DbFailException e) {
            e.printStackTrace ();
            RequestDispatcher dispatcher = request.getRequestDispatcher ("welcome.jsp");
            PrintWriter out = response.getWriter ();
            out.print ("<font color = red>Post wasn't delete from database, tray again</font>");
            dispatcher.include (request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
