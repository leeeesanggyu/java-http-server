package v5.servlet;

import httpserver.HttpRequest;
import httpserver.HttpResponse;
import httpserver.HttpServlet;

public class SearchServlet implements HttpServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>Search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + request.getParameter("q") + "</li>");
        response.writeBody("</ul>");
    }
}
