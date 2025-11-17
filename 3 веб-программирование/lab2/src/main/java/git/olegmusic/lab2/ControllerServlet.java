package git.olegmusic.lab2;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        req.setAttribute("history", context.getAttribute("history"));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getParameter("x") != null ||
                req.getParameter("y") != null ||
                req.getParameter("r") != null) {

            req.getRequestDispatcher("/area-check").forward(req, resp);
        } else {
            doGet(req, resp);
        }
    }
}
