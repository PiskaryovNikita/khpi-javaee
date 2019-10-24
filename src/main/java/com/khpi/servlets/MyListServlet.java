package com.khpi.servlets;

import com.khpi.model.Human;
import com.khpi.util.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

            flushHtmlList(response);
            return;
        }
        getServletContext().getRequestDispatcher("/error-401.html").forward(request, response);
    }

    private void flushHtmlList(HttpServletResponse response) throws IOException {
        List<Human> humans = new ArrayList<>();
        humans.add(new Human(1L, "Nikita", "Piskaryov"));
        humans.add(new Human(2L, "Pasha", "Kolcov"));
        humans.add(new Human(3L, "Eugene", "Djura"));

        StringBuilder rows = new StringBuilder("");

        humans.forEach(human -> rows.append("<tr>\n" + "        <td>")
                .append(human.getId())
                .append("</td>\n")
                .append("        <td>")
                .append(human.getName())
                .append("</td>\n")
                .append("        <td>")
                .append(human.getSurname())
                .append("</td>\n")
                .append("    </tr>"));

        PrintWriter out = response.getWriter();
        out.println("<html><head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" +
                "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\n" +
                "    <title>MyList</title>\n" +
                "</head>\n" +
                "<style>\n" +
                "    .thead {\n" +
                "        background-color: blue;\n" +
                "        color: white;\n" +
                "    }\n" +
                "</style>" +
                "<body>" +
                "<table class=\"table\">\n" +
                "    <thead class=\"thead\">\n" +
                "    <tr>\n" +
                "        <th>#</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Surname</th>\n" +
                "    </tr>\n" +
                "    </thead>");
        out.println(rows);
        out.println("</tbody></body></html>");
    }

}
