package Beans;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class YandexDiskXlsxAppender {
    private final String token;
    private final String filePath;

    public YandexDiskXlsxAppender(String token, String filePath) {
        this.token = token;
        this.filePath = filePath;
    }

    public void appendRow(List<String> cells) {
        int tries = 5;
        long sleep = 300;

        for (int attempt = 1; attempt <= tries; attempt++) {
            try {
                byte[] xlsx = downloadIfExists();
                Workbook wb = (xlsx == null) ? new XSSFWorkbook() : new XSSFWorkbook(new ByteArrayInputStream(xlsx));
                Sheet sh = wb.getNumberOfSheets() == 0 ? wb.createSheet("history") : wb.getSheetAt(0);

                if (sh.getPhysicalNumberOfRows() == 0) {
                    Row header = sh.createRow(0);
                    header.createCell(0).setCellValue("x");
                    header.createCell(1).setCellValue("y");
                    header.createCell(2).setCellValue("r");
                    header.createCell(3).setCellValue("hit");
                    header.createCell(4).setCellValue("timestamp");
                }

                int rowNum = sh.getLastRowNum() + 1;
                if (rowNum == 0) rowNum = 1;

                Row row = sh.createRow(rowNum);
                for (int i = 0; i < cells.size(); i++) {
                    row.createCell(i).setCellValue(cells.get(i));
                }

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                wb.write(bos);
                wb.close();

                upload(bos.toByteArray());
                return;

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private byte[] downloadIfExists() throws Exception {
        int code = head();
        if (code == 404) return null;
        if (code != 200) return null;

        String href = getHref("https://cloud-api.yandex.net/v1/disk/resources/download?path=" + enc(filePath));
        HttpURLConnection con = (HttpURLConnection) new URL(href).openConnection();
        con.setRequestMethod("GET");
        con.connect();

        try (InputStream in = con.getInputStream()) {
            return in.readAllBytes();
        } finally {
            con.disconnect();
        }
    }

    private void upload(byte[] content) throws Exception {
        String href = getHref("https://cloud-api.yandex.net/v1/disk/resources/upload?path=" + enc(filePath) + "&overwrite=true");
        HttpURLConnection con = (HttpURLConnection) new URL(href).openConnection();
        con.setRequestMethod("PUT");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        try (OutputStream out = con.getOutputStream()) {
            out.write(content);
        }

        int code = con.getResponseCode();
        if (code < 200 || code >= 300) {
            throw new IOException("Upload failed HTTP " + code);
        }
        con.disconnect();
    }

    private int head() throws Exception {
        String url = "https://cloud-api.yandex.net/v1/disk/resources?path=" + enc(filePath);
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "OAuth " + token);
        con.connect();
        int code = con.getResponseCode();
        con.disconnect();
        return code;
    }

    private String getHref(String apiUrl) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(apiUrl).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "OAuth " + token);
        con.connect();

        int code = con.getResponseCode();
        String body = readBody(con);
        con.disconnect();

        if (code < 200 || code >= 300) {
            throw new IOException("YaDisk API error HTTP " + code + ": " + body);
        }

        String href = extractJsonStringValue(body, "href");
        if (href == null || href.isEmpty()) {
            throw new IOException("No href in response: " + body);
        }

        return unescapeJson(href);
    }

    private static String readBody(HttpURLConnection con) {
        try (InputStream in = (con.getResponseCode() >= 200 && con.getResponseCode() < 300)
                ? con.getInputStream()
                : con.getErrorStream()) {
            if (in == null) return "";
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }

    private static String extractJsonStringValue(String json, String key) {
        String needle = "\"" + key + "\"";
        int k = json.indexOf(needle);
        if (k < 0) return null;

        int colon = json.indexOf(':', k + needle.length());
        if (colon < 0) return null;

        int firstQuote = json.indexOf('"', colon + 1);
        if (firstQuote < 0) return null;

        int i = firstQuote + 1;
        StringBuilder sb = new StringBuilder();
        boolean esc = false;
        while (i < json.length()) {
            char c = json.charAt(i);
            if (esc) {
                sb.append('\\').append(c);
                esc = false;
            } else if (c == '\\') {
                esc = true;
            } else if (c == '"') {
                break;
            } else {
                sb.append(c);
            }
            i++;
        }
        return sb.toString();
    }

    private static String unescapeJson(String s) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < s.length()) {
                char n = s.charAt(i + 1);
                if (n == '/') { out.append('/'); i++; continue; }
                if (n == '\\') { out.append('\\'); i++; continue; }
                if (n == '"') { out.append('"'); i++; continue; }
                if (n == 'n') { out.append('\n'); i++; continue; }
                if (n == 'r') { out.append('\r'); i++; continue; }
                if (n == 't') { out.append('\t'); i++; continue; }
            }
            out.append(c);
        }
        return out.toString();
    }


    private static String enc(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    public void clear() {
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sh = wb.createSheet("history");

            Row header = sh.createRow(0);
            header.createCell(0).setCellValue("x");
            header.createCell(1).setCellValue("y");
            header.createCell(2).setCellValue("r");
            header.createCell(3).setCellValue("hit");
            header.createCell(4).setCellValue("timestamp");

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            wb.write(bos);
            wb.close();

            upload(bos.toByteArray());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void overwrite(byte[] content) {
        try {
            upload(content);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки файла на Яндекс.Диск", e);
        }
    }


}
