package view;

import domain.Car;
import domain.CarType;
import domain.User;
import domain.UserType;
import exception.EntityNotFoundException;
import service.CarService;
import service.Service;
import service.UserService;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserServletController extends BaseController {

    private Service<User> service;

    public void init() {
        service = new UserService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String action = extractAction(request);

            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
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

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = service.findAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/user/UserList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/pages/user/UserForm.jsp");
        request.setAttribute("types", UserType.values());
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            User existingUser = service.findById(id);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/user/UserForm.jsp");
            request.setAttribute("types", UserType.values());
            request.setAttribute("user", existingUser);
            request.setAttribute("isEdit", true);
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listUser(request, response);
        }

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = null;


        try {
            String id = UUID.randomUUID().toString();
            String email = request.getParameter("email");
            String password = request.getParameter
                    ("password");
            String typeStr = request.getParameter("type");

            user = new User(id,
                    email, password, UserType.valueOf(typeStr));

            service.add(user);
            request.setAttribute("message", Message.buildSuccessMessage("User added successfully"));
            listUser(request, response);
        } catch (Exception e) {
            request.setAttribute("user", user);
            request.setAttribute("message", processException(e));
            request.setAttribute("isNew", true);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/user/UserForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        try {
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            String typeStr = request.getParameter("type");

            user = new User(id,
                    email, UserType.valueOf(typeStr));


            service.modify(user);
            request.setAttribute("message", Message.buildSuccessMessage("User updated successfully"));
            listUser(request, response);
        } catch (Exception e) {
            request.setAttribute("user", user);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/UserForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.remove(id);
            request.setAttribute("message", Message.buildSuccessMessage("User deleted successfully"));
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
        }
        listUser(request, response);

    }


}
