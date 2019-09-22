package com.khpi.servlets;

import com.khpi.util.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "MyList", urlPatterns = "/list")
public class MyListServlet extends HttpServlet {
    private static final Map<String, String> map = Map.of("admin", "password");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String body = HttpUtil.getBody(request);
        String userName = HttpUtil.getFormValue(body, "userName");
        String password = HttpUtil.getFormValue(body, "password");

        if (map.containsKey(userName) && map.containsValue(password)) {
            getServletContext().getRequestDispatcher("/list.html").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/error-401.html").forward(request, response);
    }

}
