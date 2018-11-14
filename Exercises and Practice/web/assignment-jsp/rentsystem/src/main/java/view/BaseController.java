package view;

import exception.EntityNotFoundException;
import exception.InfrastructureException;
import exception.ValidationException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;

public class BaseController extends HttpServlet {

    final static Logger logger = Logger.getLogger(BaseController.class);

    protected String processException(Exception e) {
        if (e instanceof ValidationException) {
            return e.getMessage();
        } else if (e instanceof EntityNotFoundException) {
            logger.error(e.getMessage());
            return e.getMessage();
        } else if (e instanceof InfrastructureException) {
            logger.error(e.getMessage());
            return "Infrastructure error, please try again later";
        } else {
            logger.error(e.getMessage());
            return "Unexpected error, please try again later";
        }
    }
}
