package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Enter Control");
        System.out.println(req.getParameter("x") + " " + req.getParameter("y") + " "  + req.getParameter("r"));
        if (req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null) {
            System.out.println("AreaCheck complete");
            getServletContext().getRequestDispatcher("/checker").forward(req, resp);
        } else if (req.getParameter("clear") != null && req.getParameter("clear").equals("true")) {
            System.out.println("Clear complete");
            getServletContext().getRequestDispatcher("/clear").forward(req, resp);
        } else {
            System.out.println("index.jsp complete");
            getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            getServletContext().getNamedDispatcher("/error_page.jsp").forward(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "ControllerServlet - определяет тип запроса, и, в зависимости от того, содержит ли запрос информацию " +
                "о координатах точки и радиусе, делегирует его обработку стартовой jsp-странице или AreaCheckServlet-у. " +
                "Все запросы внутри приложения должны передаваться этому сервлету, остальные сервлеты с веб-страниц " +
                "напрямую вызываться не должны.";
    }
}