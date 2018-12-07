package view;

import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    public FilterConfig filterConfig;

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        if(!request.getRequestURL().toString().contains("login")
        && !request.getRequestURL().toString().contains("static")) {
            User user = (User) request.getSession().getAttribute("loggedUser");
            if (user == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/common/loginForm.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        filter.doFilter(servletRequest, servletResponse);
    }

    public void init(final FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {

    }
}
