package v5;

import java.io.IOException;

public class ServerMainV5 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV5 server = new HttpServerV5(PORT);
        server.start();
    }
}
