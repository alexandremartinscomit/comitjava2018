package view;

import domain.User;
import exception.EntityNotFoundException;
import exception.InfrastructureException;
import exception.ValidationException;
import org.apache.log4j.Logger;
import view.util.Message;
import view.util.MessageType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class BaseController extends HttpServlet {

    final static Logger logger = Logger.getLogger(BaseController.class);

    protected Message processException(Exception e) {
        if (e instanceof ValidationException) {
            return Message.buildWarningMessage(e.getMessage());
        } else if (e instanceof EntityNotFoundException) {
            logger.error(e.getMessage(), e);
            return Message.buildWarningMessage(e.getMessage());
        } else if (e instanceof InfrastructureException) {
            String errorId = UUID.randomUUID().toString();
            logger.error(errorId+" - "+e.getMessage(), e);
            return Message.buildDangerMessage(errorId+": Infrastructure error, please try again later");
        } else {
            String errorId = UUID.randomUUID().toString();
            logger.error(errorId+" - "+e.getMessage(), e);
            return Message.buildDangerMessage(errorId+": Unexpected error, please try again later");
        }
    }


    protected void verifyLoggedUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User)request
                .getSession()
                .getAttribute("loggedUser");

        if (user == null){
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/common/loginForm.jsp");

            dispatcher.forward(request, response);
        }
    }
}
