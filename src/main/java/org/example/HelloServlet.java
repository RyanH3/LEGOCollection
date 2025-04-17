package org.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        // View LEGO Collection
        out.println("<table>");
        int counter = 0;
        for (LEGOSet set : App.viewItems()) {
            counter++;
            out.println("<tr>");
            out.println("<td>LEGO Set " + counter + ": " + set.getId() + "</td>");
            out.println("<td>ID: " + set.getId() + "</td>");
            out.println("<td>Pieces: " + set.getPiecesAmount() + "</td>");
            out.println("<td>Price: $" + set.getPrice() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }

    public void destroy() {
    }
}