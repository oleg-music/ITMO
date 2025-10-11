package git.olegmusic;

import com.fastcgi.FCGIInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Server {

    private static final List<Map<String, Object>> HISTORY = new ArrayList<>();
    private static final int MAX_HISTORY = 200;

    public static void main(String[] args) {
        FCGIInterface fcgi = new FCGIInterface();

        while (fcgi.FCGIaccept() >= 0) {
            long t0 = System.currentTimeMillis();

            String method = FCGIInterface.request.params.getProperty("REQUEST_METHOD");
            if (!"GET".equalsIgnoreCase(method)) {
                sendJson(400, "{\"error\":\"Only GET requests\"}");
                continue;
            }

            String qs = FCGIInterface.request.params.getProperty("QUERY_STRING");
            if (qs == null || qs.isEmpty()) {
                sendJson(400, "{\"error\":\"Missing query\"}");
                continue;
            }

            Map<String, String> p = parseQuery(qs);
            String action = p.get("action");

            if ("history".equalsIgnoreCase(action)) {
                sendJson(200, historyToJson(HISTORY));
                continue;
            }

            if ("clear".equalsIgnoreCase(action)) {
                synchronized (HISTORY) {
                    HISTORY.clear();
                }
                sendJson(200, "{\"cleared\":true,\"history\":[]}");
                continue;
            }

            String xs = p.get("x");
            String ys = p.get("y");
            String rs = p.get("r");
            if (xs == null || ys == null || rs == null) {
                sendJson(400, "{\"error\":\"Missing parameters x/y/r\"}");
                continue;
            }

            git.olegmusic.validation.Validate v = new git.olegmusic.validation.Validate();
            if (!v.check(xs, ys, rs)) {
                sendJson(400, "{\"error\":\"" + esc(v.getErr()) + "\"}");
                continue;
            }

            int x = Integer.parseInt(xs);
            BigDecimal y = new BigDecimal(ys.replace(',', '.'));
            BigDecimal r = new BigDecimal(rs.replace(',', '.'));

            boolean hit = hitTest(x, y, r);
            long workTime = System.currentTimeMillis() - t0;

            Map<String, Object> rec = new LinkedHashMap<>();
            rec.put("hit", hit);
            rec.put("x", x);
            rec.put("y", y.toPlainString());
            rec.put("r", r.toPlainString());
            rec.put("time", LocalDateTime.now().toString());
            rec.put("workTime", workTime);

            synchronized (HISTORY) {
                HISTORY.add(rec);
                if (HISTORY.size() > MAX_HISTORY) HISTORY.remove(0);
            }

            sendJson(200, mapToJson(rec));
        }
    }

    private static boolean hitTest(int x, BigDecimal y, BigDecimal r) {
        boolean inRect = (x >= 0)
                && (y.compareTo(BigDecimal.ZERO) >= 0)
                && (BigDecimal.valueOf(x).compareTo(r) <= 0)
                && (y.compareTo(r) <= 0);

        BigDecimal x2 = BigDecimal.valueOf(x).pow(2);
        BigDecimal y2 = y.pow(2);
        BigDecimal r2div4 = r.pow(2).divide(BigDecimal.valueOf(4), 20, java.math.RoundingMode.HALF_UP);

        boolean inSector = (x <= 0)
                && (y.compareTo(BigDecimal.ZERO) >= 0)
                && (x2.add(y2).compareTo(r2div4) <= 0);

        BigDecimal halfR = r.divide(BigDecimal.valueOf(2), 20, java.math.RoundingMode.HALF_UP);
        BigDecimal negHalfR = halfR.negate();

        BigDecimal xBD = BigDecimal.valueOf(x);
        boolean inTri = (x <= 0)
                && (y.compareTo(BigDecimal.ZERO) <= 0)
                && (xBD.compareTo(negHalfR) >= 0)
                && (y.compareTo(negHalfR) >= 0)
                && (xBD.add(y).compareTo(negHalfR) >= 0);

        return inRect || inSector || inTri;
    }

    private static Map<String, String> parseQuery(String qs) {
        Map<String, String> m = new LinkedHashMap<>();
        for (String part : qs.split("&")) {
            String[] kv = part.split("=", 2);
            if (kv.length == 2) m.put(urlDecode(kv[0]), urlDecode(kv[1]));
        }
        return m;
    }
    private static String urlDecode(String s) {
        try { return java.net.URLDecoder.decode(s, java.nio.charset.StandardCharsets.UTF_8); }
        catch (Exception e) { return s; }
    }

    private static String historyToJson(List<Map<String, Object>> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"history\":[");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(',');
            sb.append(mapToJson(list.get(i)));
        }
        sb.append("]}");
        return sb.toString();
    }

    private static String mapToJson(Map<String, Object> m) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<String, Object> e : m.entrySet()) {
            if (!first) sb.append(',');
            first = false;
            sb.append("\"").append(esc(e.getKey())).append("\":");
            Object v = e.getValue();
            if (v instanceof String) {
                sb.append("\"").append(esc((String) v)).append("\"");
            } else if (v instanceof Boolean || v instanceof Number) {
                sb.append(v.toString());
            } else {
                sb.append("\"").append(esc(String.valueOf(v))).append("\"");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String esc(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static void sendJson(int status, String body) {
        String statusLine = switch (status) {
            case 200 -> "200 OK";
            case 204 -> "204 No Content";
            case 400 -> "400 Bad Request";
            default -> status + " OK";
        };
        byte[] bytes = body.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        StringBuilder h = new StringBuilder();
        h.append("Status: ").append(statusLine).append("\r\n");
        h.append("Content-Type: application/json; charset=utf-8\r\n");
        h.append("Cache-Control: no-store\r\n");
        h.append("Content-Length: ").append(bytes.length).append("\r\n\r\n");
        System.out.print(h);
        try {
            System.out.write(bytes);
            System.out.flush();
        } catch (Exception ignore) {}
    }
}
