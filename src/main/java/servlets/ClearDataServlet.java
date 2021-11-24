package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("Clear") != null && req.getParameter("Clear").equals("true")) {
            getServletContext().removeAttribute("results");
            getServletContext().getRequestDispatcher("/table.jsp").forward(req, resp);
        } else getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);
    }
}
