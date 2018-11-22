package view;

import exception.EntityNotFoundException;
import exception.InfrastructureException;
import exception.ValidationException;
import org.apache.log4j.Logger;
import view.util.Message;
import view.util.MessageType;

import javax.servlet.http.HttpServlet;

public class BaseController extends HttpServlet {

    final static Logger logger = Logger.getLogger(BaseController.class);

    protected Message processException(Exception e) {
        if (e instanceof ValidationException) {
            return Message.buildWarningMessage(e.getMessage());
        } else if (e instanceof EntityNotFoundException) {
            logger.error(e.getMessage());
            return Message.buildWarningMessage(e.getMessage());
        } else if (e instanceof InfrastructureException) {
            logger.error(e.getMessage());
            return Message.buildDangerMessage("Infrastructure error, please try again later");
        } else {
            logger.error(e.getMessage());
            return Message.buildDangerMessage("Unexpected error, please try again later");
        }
    }
}
