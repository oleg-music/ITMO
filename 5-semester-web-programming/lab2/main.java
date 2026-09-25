import com.fastcgi.FCGIInterface;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        FCGIInterface fcgi = new FCGIInterface();

        while (fcgi.FCGIaccept() >= 0) {
            String method = System.getProperty("REQUEST_METHOD");

            int contentLength = Integer.parseInt(System.getProperty("CONTENT_LENGTH", "0"));

            String body = new String(System.in.readNBytes(contentLength), StandardCharsets.UTF_8);

            System.out.print("HTTP/1.1 200 OK\r\n" + "Content-Type: application/json; charset=UTF-8\r\n\r\n" + "{\"status\":\"ok\"}");
        }
    }
}