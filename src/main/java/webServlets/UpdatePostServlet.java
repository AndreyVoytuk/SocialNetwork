package webServlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UpdatePostServlet", urlPatterns = "/updatePost")
public class UpdatePostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession (true);
        session.setAttribute ("content", request.getParameter ("postContent"));
        session.setAttribute ("postId", request.getParameter ("postId"));
        RequestDispatcher dispatcher = request.getRequestDispatcher ("updatePost.jsp");
        dispatcher.forward (request, response);
    }
}
