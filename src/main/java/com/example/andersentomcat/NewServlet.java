package com.example.andersentomcat;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "newServlet", value = "/new-servlet")
public class NewServlet extends HttpServlet {
    private String message;

    public void init() {
        try {
            Process process = Runtime.getRuntime().exec("env");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder envVariables = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                envVariables.append(line).append("<br>");
            }
            reader.close();
            message = "Environment Variables:<br>" + envVariables.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}