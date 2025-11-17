package git.olegmusic.lab2;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/area-check")
public class AreaCheckServlet extends HttpServlet {

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        long start = System.nanoTime();

        String xStr = req.getParameter("x");
        String yStr = req.getParameter("y");
        String rStr = req.getParameter("r");

        BigDecimal xBD = parseBigDecimal(xStr);
        BigDecimal yBD = parseBigDecimal(yStr);
        Integer r = parseInt(rStr);

        String error = null;

        if (xBD == null
                || xBD.compareTo(BigDecimal.valueOf(-3)) < 0
                || xBD.compareTo(BigDecimal.valueOf(3)) > 0) {

            error = "Некорректное значение X (ожидается число в диапазоне [-3; 3]).";

        } else if (yBD == null
                || yBD.compareTo(BigDecimal.valueOf(-3)) < 0
                || yBD.compareTo(BigDecimal.valueOf(3)) > 0) {

            error = "Некорректное значение Y (ожидается число в диапазоне [-3; 3]).";

        } else if (r == null || r < 1 || r > 5) {
            error = "Некорректное значение R (ожидается одно из {1,2,3,4,5}).";
        }

        if (error != null) {
            req.setAttribute("error", error);
            ServletContext context = getServletContext();
            req.setAttribute("history", context.getAttribute("history"));
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        BigDecimal rBD = BigDecimal.valueOf(r);

        boolean hit = PointChecker.check(xBD, yBD, rBD);

        long execTimeMs = (System.nanoTime() - start) / 1_000_000;
        String time = LocalDateTime.now().format(TIME_FORMAT);

        PointResult result = new PointResult(
                xStr,
                yStr,
                rStr,
                hit,
                time,
                execTimeMs
        );

        ServletContext context = getServletContext();
        synchronized (context) {
            @SuppressWarnings("unchecked")
            List<PointResult> history = (List<PointResult>) context.getAttribute("history");
            if (history == null) {
                history = new ArrayList<>();
                context.setAttribute("history", history);
            }
            history.add(result);
        }

        req.setAttribute("result", result);
        req.setAttribute("history", context.getAttribute("history"));

        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    private Integer parseInt(String s) {
        if (s == null) return null;
        s = s.trim();
        if (s.isEmpty()) return null;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private BigDecimal parseBigDecimal(String s) {
        if (s == null) return null;
        s = s.trim().replace(',', '.');
        if (s.isEmpty()) return null;
        try {
            return new BigDecimal(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
