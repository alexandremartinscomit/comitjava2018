package view;

import domain.Car;
import domain.RentOrder;
import domain.User;
import exception.EntityNotFoundException;
import service.CarService;
import service.RentOrderService;
import service.Service;
import service.UserService;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class RentOrderServletController extends BaseController {

    private Service<RentOrder> service;
    private Service<Car> carService;
    private Service<User> userService;
    private DateTimeFormatter DATE_TIME_FORMATTER;

    public void init() {
        service = new RentOrderService();
        userService = new UserService();
        carService = new CarService();
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")
                .withZone(ZoneId.systemDefault());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String action = extractAction(request);

            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertRentOrder(request, response);
                    break;
                case "/delete":
                    deleteRentOrder(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateRentOrder(request, response);
                    break;
                default:
                    listRentOrder(request, response);
                    break;
            }
        } catch (ServletException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

    private String extractAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        } else {
            return pathInfo;
        }
    }

    private void listRentOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RentOrder> listRentOrder = service.findAll();
        request.setAttribute("listRentOrder", listRentOrder);
        request.setAttribute("dateFormatter", DATE_TIME_FORMATTER);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/rentOrder/RentOrderList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/rentOrder/RentOrderForm.jsp");
        request.setAttribute("users", userService.findAll());
        request.setAttribute("cars", carService.findAll());
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            RentOrder existingRentOrder = service.findById(id);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/rentOrder/RentOrderForm.jsp");
            request.setAttribute("dateFormatter", DATE_TIME_FORMATTER);
            request.setAttribute("users", userService.findAll());
            request.setAttribute("cars", carService.findAll());
            request.setAttribute("rentOrder", existingRentOrder);
            request.setAttribute("isEdit", true);
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listRentOrder(request, response);
        }

    }

    private void insertRentOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RentOrder rentOrder = null;




        try {
            String id = UUID.randomUUID().toString();
            Car car = new Car(request.getParameter("car"));
            User user = new User(request.getParameter("user"));

            Instant rentDate = Instant.from(DATE_TIME_FORMATTER.parse(request.getParameter("rentDate")));
            Instant expectedDeliveryDate = Instant.from(DATE_TIME_FORMATTER.parse(request.getParameter("expectedDeliveryDate")));
            float price = Float.valueOf(request.getParameter("price"));
            boolean isFreeUpgrade = Boolean.valueOf(request.getParameter("freeUpgrade"));
            String observations = request.getParameter("observations");

            rentOrder = new RentOrder(id, car, user, rentDate, expectedDeliveryDate, price, isFreeUpgrade, observations);

            service.add(rentOrder);
            request.setAttribute("message", Message.buildSuccessMessage("RentOrder added successfully"));
            listRentOrder(request, response);
        } catch (Exception e) {
            request.setAttribute("rentOrder", rentOrder);
            request.setAttribute("message", processException(e));
            request.setAttribute("isNew", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/rentOrder/RentOrderForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateRentOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RentOrder rentOrder = null;
        try {
            String id = request.getParameter("id");
            Car car = new Car(request.getParameter("car"));
            User user = new User(request.getParameter("user"));

            Instant rentDate = Instant.from(DATE_TIME_FORMATTER.parse(request.getParameter("rentDate")));
            Instant expectedDeliveryDate = Instant.from(DATE_TIME_FORMATTER.parse(request.getParameter("expectedDeliveryDate")));
            float price = Float.valueOf(request.getParameter("price"));
            boolean isFreeUpgrade = Boolean.valueOf(request.getParameter("freeUpgrade"));
            String observations = request.getParameter("observations");

            rentOrder = new RentOrder(id, car, user, rentDate, expectedDeliveryDate, price, isFreeUpgrade, observations);

            service.modify(rentOrder);
            request.setAttribute("message", Message.buildSuccessMessage("Rent Order updated successfully"));
            listRentOrder(request, response);
        } catch (Exception e) {
            request.setAttribute("rentOrder", rentOrder);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/rentOrder/RentOrderForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteRentOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.remove(id);
            request.setAttribute("message", Message.buildSuccessMessage("Rent Order deleted successfully"));
            listRentOrder(request, response);
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
            listRentOrder(request, response);
        }

    }
}
