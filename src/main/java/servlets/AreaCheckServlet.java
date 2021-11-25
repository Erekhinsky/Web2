package servlets;

import utils.Result;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Enter Checked");
        long start = System.nanoTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String currentTime = formatter.format(date);
        if (req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null) {
            if (validateData(req.getParameter("x"), req.getParameter("y"), req.getParameter("r"))) {
                double x = Double.parseDouble(req.getParameter("x"));
                double y = Double.parseDouble(req.getParameter("y"));
                double r = Double.parseDouble(req.getParameter("r"));
                System.out.println(r);
                boolean hitFact = checkHit(x, y, r);
                String executionTime = String.format("%.6f", (System.nanoTime() - start) * 10e-9).replace(",", ".");
                Result result = new Result(x, y, r, currentTime, executionTime, hitFact);
                ArrayList<Result> results;
                if (getServletContext().getAttribute("results") != null) {
                    results = (ArrayList<Result>) getServletContext().getAttribute("results");
                } else results = new ArrayList<>();
                results.add(result);
                System.out.println("Список результата:");
                results.iterator().forEachRemaining(System.out::println);
                getServletContext().setAttribute("results", results);
            }
            getServletContext().getRequestDispatcher("/table.jsp").forward(req, resp);
        } else getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/error_page.jsp").forward(req, resp);
    }

    private boolean validateData(String x, String y, String r) {
       return (validateX(x) && validateY(y) && validateR(r));
    }

    private boolean validateX(String x) {
        try {
            double xNum = Double.parseDouble(x);
            System.out.println("X OK " + x);
            return xNum > -3 && xNum < 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateY(String y) {
//        String[] values = {"-2.0", "-1.5", "-1.0", "-0.5", "0", "2.0", "1.5", "1.0", "0.5"};
//        for (String value : values) {
//            if (y.equals(value)) {
//                System.out.println("Y OK");
//                return true;
//            }
//        }
//        return false;

        try {
            double yNum = Double.parseDouble(y);
            System.out.println("Y OK: " + y);
            return (yNum >= -2 && yNum <=2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateR(String r) {
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        for (String value : values) {
            if (r.equals(value)) {
                System.out.println("R OK: " + r);
                return true;
            }
        }
        System.out.println("R NOT OK: " + r);
        return false;
    }

    private boolean checkCircle(double x, double y, double r) {
        return (Math.pow(x, 2) + Math.pow(y, 2)) <= ((Math.pow(r / 2, 2))) && (x >= 0) && (y >= 0);
    }

    private boolean checkTriangle(double x, double y, double r) {
        return ((x <= r/2) && (x >= 0) && (y <= 0) && (y >= -r)) && (y >= x/2 - r);
    }

    private boolean checkRectangle(double x, double y, double r) {
        return (x <= 0) && (x >= -r) && (y >= 0) && (y <= r);
    }

    private boolean checkHit(double x, double y, double r) {
        return checkCircle(x, y, r) || checkTriangle(x, y, r) || checkRectangle(x, y, r);
    }

    @Override
    public String getServletInfo() {
        return "AreaCheckServlet - осуществляет проверку попадания точки в область на координатной плоскости и " +
                "формирует HTML-страницу с результатами проверки. Должен обрабатывать все запросы, " +
                "содержащие сведения о координатах точки и радиусе области.";
    }
}