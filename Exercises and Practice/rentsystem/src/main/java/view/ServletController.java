package view;

import domain.Car;
import domain.CarType;
import service.CarService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletController extends HttpServlet {
    private CarService carService;

    public void init() {
        carService = new CarService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();

        try {
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
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listCar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Car> listCar = carService.findAll();
        request.setAttribute("listCar", listCar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/CarList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/CarForm.jsp");
        request.setAttribute("types", CarType.values());
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        Car existingCar = carService.findCarById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/CarForm.jsp");
        request.setAttribute("types", CarType.values());
        request.setAttribute("car", existingCar);
        dispatcher.forward(request, response);

    }

    private void insertCar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = UUID.randomUUID().toString();
        String plate = request.getParameter("plate");
        String color = request.getParameter("color");
        int year = Integer.parseInt(request.getParameter("year"));
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String typeStr = request.getParameter("type");

        Car car = new Car(id,
                          plate, color, year, brand, model, CarType.valueOf(typeStr));
        carService.addCar(car);
        response.sendRedirect("list");
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String id = request.getParameter("id");
        String plate = request.getParameter("plate");
        String color = request.getParameter("color");
        int year = Integer.parseInt(request.getParameter("year"));
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String typeStr = request.getParameter("type");

        Car car = new Car(id,
                          plate, color, year, brand, model, CarType.valueOf(typeStr));
        carService.modifyCar(car);
        response.sendRedirect("list");
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");

        Car car = new Car(id);
        carService.removeCar(car);
        response.sendRedirect("list");

    }
}
