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
//        HttpSession session = req.getSession();
//        resp.setContentType("text/html;charset=UTF-8");
//        List<String> tableRows = (List) session.getAttribute("tableRows");
//
//        bean = (PointsTableBean) req.getSession().getAttribute("pointsBean");
//
//
//        if (tableRows == null) {
//            tableRows = new ArrayList<String>();
//            session.setAttribute("tableRows", tableRows);
//            tableRows.add("<table id='outputTable'><tr>" +
//                    "<th>x</th>" +
//                    "<th>y</th>" +
//                    "<th>r</th>" +
//                    "<th>Точка входит в ОДЗ</th>" +
//                    "<th>Текущее время</th></tr>");
//        }
//        double x = Double.parseDouble(req.getParameter("x"));
//        double y = Double.parseDouble(req.getParameter("y"));
//        double r = Double.parseDouble(req.getParameter("r"));
//        String key = req.getParameter("key");
//
//
//        try (PrintWriter writer = resp.getWriter()) {
//            if (validateData(x, y, r, key)) {
//                tableRows.add(new Point(x, y, r).toString());
//                for (String tableRow : tableRows) writer.println(tableRow);
//            } else resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//        }
        long start = System.nanoTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String currentTime = formatter.format(date);
        String key = req.getParameter("key");
        if (req.getParameter("x") != null && req.getParameter("y") != null && req.getParameter("r") != null) {
            if (validateData(req.getParameter("x"), req.getParameter("y"), req.getParameter("r"), key)) {
                double x = Double.parseDouble(req.getParameter("x"));
                double y = Double.parseDouble(req.getParameter("y"));
                double r = Double.parseDouble(req.getParameter("r"));
                boolean hitFact = checkHit(x, y, r);
                String executionTime = String.format("%.6f", (System.nanoTime() - start) * 10e-9).replace(",", ".");
                Result result = new Result(x, y, r, currentTime, executionTime, hitFact);
                ArrayList<Result> results;
                if (getServletContext().getAttribute("results") != null) {
                    results = (ArrayList<Result>) getServletContext().getAttribute("results");
                } else results = new ArrayList<>();
                results.add(result);
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

//    private void updateTable(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        HttpSession session = req.getSession();
//        List<String> tableRows = (List) session.getAttribute("tableRows");
//        if (tableRows == null) {
//            tableRows = new ArrayList<>();
//            session.setAttribute("tableRows", tableRows);
//            tableRows.add("<table id='outputTable'><tr>" +
//                    "<th>x</th>" +
//                    "<th>y</th>" +
//                    "<th>r</th>" +
//                    "<th>Точка входит в ОДЗ</th>" +
//                    "<th>Текущее время</th></tr>");
//        }
//        try (PrintWriter writer = resp.getWriter()) {
//            for (Object tableRow : tableRows) writer.println(tableRow);
//        }
//    }

    private boolean validateData(String x, String y, String r, String key) {
        if (key.equals("button"))
            return (validateX(x) && validateY(y) && validateR(r));
        else if (key.equals("svg")) return (Double.parseDouble(r) > 0 && Double.parseDouble(r) <= 15);
        else return false;
    }

    private boolean validateX(String x) {
        try {
            double xNum = Double.parseDouble(x);
            return xNum > -3 && xNum < 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateY(String y) {
        String[] values = {"-2.0", "-1.5", "-1.0", "-0.5", "0", "2.0", "1.5", "1.0", "0.5"};
        for (String value : values) {
            if (y.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateR(String r) {
        String[] values = {"1.0", "2.0", "3.0", "4.0", "5.0"};
        for (String value : values) {
            if (r.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCircle(double x, double y, double r) {
        return (Math.pow(x, 2) + Math.pow(y, 2)) <= ((Math.pow(r/2, 2))) && (x >= 0) && (y >= 0);
    }

    private boolean checkTriangle(double x, double y, double r) {
        return ((x <= r) && (x >= 0) && (y <= 0) && (y >= -r)) && (y >= x - r);
    }

    private boolean checkRectangle(double x, double y, double r) {
        return (x <= 0) && (x >= -r/2) && (y >= 0) && (y <= r);
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