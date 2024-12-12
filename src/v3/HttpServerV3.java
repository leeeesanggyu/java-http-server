package v3;

import v2.HttpRequestHandlerV2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.Logger.log;

public class HttpServerV3 {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final int port;

    public HttpServerV3(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            log("서버 시작 port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(new HttpRequestHandlerV3(socket));
            }
        }
    }
}
