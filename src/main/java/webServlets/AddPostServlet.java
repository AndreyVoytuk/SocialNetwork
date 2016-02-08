package webServlets;

import JdbcDatasource.dao.PostDao;
import JdbcDatasource.exceptions.DbFailException;
import JdbcDatasource.implementDao.PostDaoImpl;
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

@WebServlet(name = "AddPostServlet", urlPatterns = "/addPost")
public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ("text/html");
        Post post = new Post ();
        HttpSession session = request.getSession (true);
        User user = (User) session.getAttribute ("user");
        post.setPostAuthor (user.getUserId ());
        post.setPostTitle (request.getParameter ("title"));
        post.setPostContent (request.getParameter ("content"));

        try {
            PostDao postDao = new PostDaoImpl ();
            postDao.createPost (post);
            List<Post> allPosts = postDao.getAllPosts (user.getUserId ());
            session.setAttribute ("allPosts", allPosts);
            response.sendRedirect ("/welcome.jsp");
        } catch (DbFailException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher ("/welcome.jsp");
            PrintWriter out = response.getWriter ();
            out.print ("<font color = red> Post wasn't saved, tray again</font>");
            dispatcher.include (request, response);
        }
    }

}
