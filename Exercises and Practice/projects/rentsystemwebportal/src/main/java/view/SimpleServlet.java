package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String name = request.getParameter("name");
        response.getWriter().println("<html><body>");
	    response.getWriter().println("<h1>Hello "+name+"</h1>");
        response.getWriter().println("</body></html>");
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String userName = request.getParameter("username");
	    String password = request.getParameter("password");

	    if(userName.equals("alex") && password.equals("123456")){
	        response.getWriter().println("Hello, "+userName);
        } else {
            response.getWriter().println("Your password or username is not correct");
        }



    }
}