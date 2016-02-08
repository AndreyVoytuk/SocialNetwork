package webServlets;

import JdbcDatasource.dao.LikeDao;
import JdbcDatasource.exceptions.DbFailException;
import JdbcDatasource.implementDao.LikeDaoImpl;
import bean.Like;
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

@WebServlet(name = "LikePostServlet", urlPatterns = "/likePost")
public class LikePostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession (true);
        User user = (User) session.getAttribute ("user");
        Like like = new Like ();
        like.setLikeAuthor (user.getUserId ());
        int postId = Integer.parseInt (request.getParameter ("postId"));
        like.setLikePost (postId);

        try {
            LikeDao likeDao = new LikeDaoImpl ();
            likeDao.addLike (like);
            List<Like> allLikes = likeDao.getAllLike ();
            int amountLikes = likeDao.getAmountLike (postId);
            session.setAttribute ("allLikes", allLikes);
            session.setAttribute ("amountLikes", amountLikes);
            session.setAttribute ("likePost", like.getLikePost ());
            response.sendRedirect ("/welcome.jsp");
        } catch (DbFailException e) {
            e.printStackTrace ();
            RequestDispatcher dispatcher = request.getRequestDispatcher ("welcome.jsp");
            dispatcher.forward (request, response);
            PrintWriter out = response.getWriter ();
            out.print ("<font color=green>Like wasn't added, try again");
            dispatcher.include (request, response);
        }
    }
}
