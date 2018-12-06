package view;

import domain.User;
import service.AuthenticationService;
import service.UserService;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends BaseController {

    private UserService userService;

    private AuthenticationService authenticationService;

    public void init(){
        userService = new UserService();
        authenticationService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = userService.findByEmail(email);

            if (authenticationService.verifyUserPassword(password, user.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", user);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                req.setAttribute("message", Message.buildWarningMessage("The email or password are not correct!"));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/common/loginForm.jsp");
                dispatcher.forward(req, resp);
            }
        }catch (Exception e){
            req.setAttribute("message", processException(e));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/common/loginForm.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
