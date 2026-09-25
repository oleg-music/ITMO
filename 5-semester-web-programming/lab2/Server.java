import com.fastcgi.FCGIInterface;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws Exception {
        FCGIInterface fcgi = new FCGIInterface();

        List<Result> history = new ArrayList<>();

        while (fcgi.FCGIaccept() >= 0) {
            long startTime = System.nanoTime();

            String method = System.getProperty("REQUEST_METHOD");

            if (!"POST".equals(method)) {
                sendResponse("405 Method Not Allowed", "{\"error\":\"Only POST requests are allowed\"}");
                continue;
            }

            int contentLength = Integer.parseInt(System.getProperty("CONTENT_LENGTH", "0"));

            String body = new String(System.in.readNBytes(contentLength), StandardCharsets.UTF_8);

            Map<String, String> params = new HashMap<>();

            for (String pair : body.split("&")) {
                String[] parts = pair.split("=", 2);

                if (parts.length == 2) {
                    params.put(parts[0], parts[1]);
                }
            }

            String xText = params.get("x");
            String yText = params.get("y");
            String rText = params.get("r");

            if (xText == null || yText == null || rText == null) {
                sendResponse("400 Bad Request", "{\"error\":\"Missing x, y or r parameter\"}");
                continue;
            }

            double x;
            double y;
            double r;

            try {
                x = Double.parseDouble(xText);
                y = Double.parseDouble(yText);
                r = Double.parseDouble(rText);
            } catch (NumberFormatException e) {
                sendResponse("400 Bad Request", "{\"error\":\"x, y and r must be numbers\"}");
                continue;
            }

            if (y <= -3 || y >= 3) {
                sendResponse("400 Bad Request", "{\"error\":\"y must be in (-3, 3)\"}");
                continue;
            }

            if (r <= 1 || r >= 4) {
                sendResponse("400 Bad Request", "{\"error\":\"r must be in (1, 4)\"}");
                continue;
            }

            if (x != -5 && x != -4 && x != -3 && x != -2 && x != -1 && x != 0 && x != 1 && x != 2 && x != 3) {
                sendResponse("400 Bad Request", "{\"error\":\"invalid x value\"}");
                continue;
            }

            boolean hit = isPointInside(x, y, r);

            long currentTime = System.currentTimeMillis();
            long executionTime = System.nanoTime() - startTime;

            Result result = new Result(x, y, r, hit, currentTime, executionTime);

            history.add(result);

            sendResponse(
                    "200 OK",
                    historyToJson(history)
            );
        }
    }

    private static void sendResponse(String status, String body) {
        System.out.print("HTTP/1.1 " + status + "\r\n" + "Content-Type: application/json; charset=UTF-8\r\n\r\n" + body);
    }

    private static boolean isPointInside(double x, double y, double r) {
        boolean inRectangle = x >= -r && x <= 0 && y >= -r / 2 && y <= 0;

        boolean inCircle = x <= 0 && y >= 0 && x * x + y * y <= (r / 2) * (r / 2);

        boolean inTriangle = x >= 0 && y <= 0 && y >= 2 * x - r;

        return inRectangle || inCircle || inTriangle;
    }

    private static class Result {
        double x;
        double y;
        double r;
        boolean hit;
        long timestamp;
        long executionTime;

        Result(double x, double y, double r, boolean hit, long timestamp, long executionTime) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.hit = hit;
            this.timestamp = timestamp;
            this.executionTime = executionTime;
        }
    }

    private static String resultToJson(Result result) {
        return "{\"x\":" + result.x + ",\"y\":" + result.y + ",\"r\":" + result.r + ",\"hit\":" + result.hit + ",\"timestamp\":" + result.timestamp + ",\"executionTime\":" + result.executionTime + "}";
    }

    private static String historyToJson(List<Result> history) {
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < history.size(); i++) {
            if (i > 0) {
                json.append(",");
            }

            json.append(resultToJson(history.get(i)));
        }

        json.append("]");

        return json.toString();
    }
}