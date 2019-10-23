package com.khpi.filter;

import com.khpi.excpetion.CodedException;
import com.khpi.model.User;
import com.khpi.repository.api.UserRepository;
import com.khpi.repository.impl.JCFUserRepository;
import com.khpi.util.HttpUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/**")
public class AuthorizationFilter extends HttpFilter {
    private final UserRepository userRepository = new JCFUserRepository();

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request.getSession(false) == null) {
            getServletContext().getRequestDispatcher("/login.html").forward(request, response);
            return;
        }

        if (request.getSession().getAttribute("userName") != null
        && request.getSession().getAttribute("password") != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String body = HttpUtil.getBody(request);
        String userName = HttpUtil.getFormValue(body, "userName");
        String password = HttpUtil.getFormValue(body, "password");

        User user = userRepository.findByUserName(userName);

        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("userName", userName);
            request.getSession().setAttribute("password", password);

            filterChain.doFilter(request, response);
        } else {
            throw new CodedException(401, "Unauthorized user " + userName);
        }
    }
}
