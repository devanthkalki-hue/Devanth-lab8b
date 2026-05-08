package com.cookieservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CookieServlet")

public class CookieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    int count = 0;

    // Handle GET Request
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // Get user name from textbox
        String userName = request.getParameter("userName");

        // Create Cookie
        if (userName != null && !userName.isEmpty()) {

            Cookie userCookie = new Cookie("user", userName);

            // Cookie expiry time = 30 seconds
            userCookie.setMaxAge(30);

            response.addCookie(userCookie);
        }

        // Read Existing Cookies
        Cookie[] cookies = request.getCookies();

        String existingUser = null;

        out.println("<html>");
        out.println("<head><title>Cookie Example</title></head>");
        out.println("<body bgcolor='lightyellow'>");

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("user")) {

                    existingUser = cookie.getValue();
                }
            }
        }

        // If cookie exists
        if (existingUser != null) {

            count++;

            out.println("<h2 style='color:blue;'>");
            out.println("Welcome back " + existingUser + "!");
            out.println("</h2>");

            out.println("<h2 style='color:green;'>");
            out.println("You have visited this page "
                    + count + " times");
            out.println("</h2>");

            // Display all cookies
            out.println("<h3>List of Cookies</h3>");

            out.println("<table border='1' cellpadding='5'>");

            out.println("<tr>");
            out.println("<th>Cookie Name</th>");
            out.println("<th>Cookie Value</th>");
            out.println("</tr>");

            for (Cookie cookie : cookies) {

                out.println("<tr>");

                out.println("<td>"
                        + cookie.getName()
                        + "</td>");

                out.println("<td>"
                        + cookie.getValue()
                        + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("<br><br>");
            out.println("<h3 style='color:red;'>");
            out.println("Cookie will expire in 30 seconds");
            out.println("</h3>");
        }

        // First time user
        else {

            out.println("<h2 style='color:red;'>");
            out.println("Welcome Guest! Please enter your name");
            out.println("</h2>");

            out.println("<form action='CookieServlet' method='get'>");

            out.println("Enter Name : ");
            out.println("<input type='text' name='userName'>");

            out.println("<input type='submit' value='Submit'>");

            out.println("</form>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}