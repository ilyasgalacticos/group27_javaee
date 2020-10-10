package kz.bitlab.group27.servlets;

import kz.bitlab.group27.db.DBManager;
import kz.bitlab.group27.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");

        String redirect = "/register?passworderror&email="+(email!=null?email:"")+"&full_name="+(fullName!=null?fullName:"");

        if(rePassword.equals(password)) {

            redirect =  "/register?usererror&email="+(email!=null?email:"")+"&full_name="+(fullName!=null?fullName:"");

            Users user = DBManager.getUserByEmail(email);

            if (user == null) {

                Users newUser = new Users(null, email, password, fullName, "/images/default_user.png");
                DBManager.addUser(newUser);

                redirect = "/register?success";

            }

        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
