package was.v6;

import was.httpserver.ServletManager;
import was.v5.HttpRequestHandlerV5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.Logger.log;

public class HttpServerV6 {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final int port;
    private final ServletManager servletManager;

    public HttpServerV6(int port, ServletManager servletManager) {
        this.port = port;
        this.servletManager = servletManager;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            log("서버 시작 port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(new HttpRequestHandlerV6(socket, servletManager));
            }
        }
    }
}
