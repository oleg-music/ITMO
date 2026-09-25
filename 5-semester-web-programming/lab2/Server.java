import com.fastcgi.FCGIInterface;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;

public class Server {
    public static void main(String[] args) throws Exception {
        FCGIInterface fcgi = new FCGIInterface();

        while (fcgi.FCGIaccept() >= 0) {
            String method = System.getProperty("REQUEST_METHOD");

            int contentLength = Integer.parseInt(System.getProperty("CONTENT_LENGTH", "0"));

            String body = new String(System.in.readNBytes(contentLength), StandardCharsets.UTF_8);

            Map<String, String> params = new HashMap<>();

            for (String pair : body.split("&")) {
                String[] parts = pair.split("=", 2);
                params.put(parts[0], parts[1]);
            }

            String xText = params.get("x");
            String yText = params.get("y");
            String rText = params.get("r");

            if (!"POST".equals(method)) {
                System.out.print(
                        "HTTP/1.1 405 Method Not Allowed\r\n" +
                                "Content-Type: application/json; charset=UTF-8\r\n\r\n" +
                                "{\"error\":\"Only POST requests are allowed\"}"
                );
                continue;
            }

            if (xText == null || yText == null || rText == null) {
                System.out.print(
                        "HTTP/1.1 400 Bad Request\r\n" +
                                "Content-Type: application/json; charset=UTF-8\r\n\r\n" +
                                "{\"error\":\"Missing x, y or r parameter\"}"
                );
                continue;
            }


            System.out.print("HTTP/1.1 200 OK\r\n" + "Content-Type: application/json; charset=UTF-8\r\n\r\n" + "{\"status\":\"ok\"}");
        }
    }
}