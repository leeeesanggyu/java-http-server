package v5;

import httpserver.ServletManager;
import httpserver.servlet.DiscardServlet;
import v5.servlet.HomeServlet;
import v5.servlet.SearchServlet;
import v5.servlet.Site1Servlet;
import v5.servlet.Site2Servlet;

import java.io.IOException;

public class ServerMainV5 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServletManager servletManager = new ServletManager();
        servletManager.add("/", new HomeServlet());
        servletManager.add("/site1", new Site1Servlet());
        servletManager.add("/site2", new Site2Servlet());
        servletManager.add("/search", new SearchServlet());
        servletManager.add("/favicon.ico", new DiscardServlet());

        HttpServerV5 server = new HttpServerV5(PORT, servletManager);
        server.start();
    }
}
