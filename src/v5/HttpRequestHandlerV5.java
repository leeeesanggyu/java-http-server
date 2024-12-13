package v5;

import httpserver.HttpRequest;
import httpserver.HttpResponse;
import httpserver.HttpServlet;
import httpserver.servlet.NotFoundServlet;
import v5.servlet.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.Logger.log;

public class HttpRequestHandlerV5 implements Runnable {

    private final Socket socket;
    private final HashMap<String, HttpServlet> httpServletHashMap = new HashMap<>();

    public HttpRequestHandlerV5(Socket socket) {
        this.socket = socket;
        httpServletHashMap.put("/", new HomeServlet());
        httpServletHashMap.put("/site1", new Site1Servlet());
        httpServletHashMap.put("/site2", new Site2Servlet());
        httpServletHashMap.put("/search", new SearchServlet());
    }

    @Override
    public void run() {
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)) {

            HttpRequest request = new HttpRequest(reader);
            HttpResponse response = new HttpResponse(writer);

            if (request.getPath().contains("/favicon.ico")) {
                log("favicon 요청");
                return;
            }

            log("HTTP 요청 정보 출력");
            System.out.println(request);

            log("HTTP 응답 생성중");
            httpServletHashMap.getOrDefault(request.getPath(), new NotFoundServlet())
                    .service(request, response);

            response.flush();
            log("HTTP 응답 전달 완료");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
