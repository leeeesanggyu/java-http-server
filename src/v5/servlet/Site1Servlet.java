package v5.servlet;

import httpserver.HttpRequest;
import httpserver.HttpResponse;
import httpserver.HttpServlet;

public class Site1Servlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>site1</h1>");
    }
}
