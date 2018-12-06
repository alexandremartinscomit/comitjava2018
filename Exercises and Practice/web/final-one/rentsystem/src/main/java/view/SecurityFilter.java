package view;

import domain.User;
import service.AuthenticationService;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        if(!request.getServletPath().contains("login") &&
                !request.getServletPath().contains("error")) {
            User user = (User) request.getSession().getAttribute("loggedUser");
            if (user == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/common/loginForm.jsp");
                dispatcher.forward(request, response);
            }
        }
        filter.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
