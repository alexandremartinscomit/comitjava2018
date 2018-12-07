package view;

import domain.Car;
import domain.CarType;
import exception.EntityNotFoundException;
import service.CarService;
import service.Service;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CarServletController extends BaseController {
    private Service<Car> service;

    public void init() {
        service = new CarService();
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
                    insertCar(request, response);
                    break;
                case "/delete":
                    deleteCar(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCar(request, response);
                    break;
                default:
                    listCar(request, response);
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

    private void listCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> listCar = service.findAll();
        request.setAttribute("listCar", listCar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/car/CarList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/car/CarForm.jsp");
        request.setAttribute("types", CarType.values());
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            Car existingCar = service.findById(id);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/car/CarForm.jsp");
            request.setAttribute("types", CarType.values());
            request.setAttribute("car", existingCar);
            request.setAttribute("isEdit", true);
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listCar(request, response);
        }

    }

    private void insertCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Car car = null;


        try {
            String id = UUID.randomUUID().toString();
            String plate = request.getParameter("plate");
            String color = request.getParameter("color");
            int year = Integer.parseInt(request.getParameter("year"));
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String typeStr = request.getParameter("type");

            car = new Car(id,
                    plate, color, year, brand, model, CarType.valueOf(typeStr));

            service.add(car);
            request.setAttribute("message", Message.buildSuccessMessage("Car added successfully"));
            listCar(request, response);
        } catch (Exception e) {
            request.setAttribute("car", car);
            request.setAttribute("message", processException(e));
            request.setAttribute("isNew", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/car/CarForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car car = null;
        try {
            String id = request.getParameter("id");
            String plate = request.getParameter("plate");
            String color = request.getParameter("color");
            int year = Integer.parseInt(request.getParameter("year"));
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String typeStr = request.getParameter("type");

            car = new Car(id,
                    plate, color, year, brand, model, CarType.valueOf(typeStr));

            service.modify(car);
            request.setAttribute("message", Message.buildSuccessMessage("Car updated successfully"));
            listCar(request, response);
        } catch (Exception e) {
            request.setAttribute("car", car);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/car/CarForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.remove(id);
            request.setAttribute("message", Message.buildSuccessMessage("Car deleted successfully"));
            listCar(request, response);
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
            listCar(request, response);
        }

    }
}
