package kz.bitlab.group27.servlets;

import kz.bitlab.group27.db.Cities;
import kz.bitlab.group27.db.Countries;
import kz.bitlab.group27.db.DBManager;
import kz.bitlab.group27.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");
        Long cityId = 0L;
        try {
            cityId = Long.parseLong(request.getParameter("city_id"));
        }catch (Exception e){

        }

        String redirect = "/register?passworderror&email="+(email!=null?email:"")+"&full_name="+(fullName!=null?fullName:"");

        if(rePassword.equals(password)) {

            redirect =  "/register?usererror&email="+(email!=null?email:"")+"&full_name="+(fullName!=null?fullName:"");

            Users user = DBManager.getUserByEmail(email);

            if (user == null) {

                Cities city = DBManager.getCityById(cityId);

                redirect =  "/register?cityerror&email="+(email!=null?email:"")+"&full_name="+(fullName!=null?fullName:"");

                if(city!=null) {

                    Users newUser = new Users(null, email, password, fullName, "/images/default_user.png", city);
                    DBManager.addUser(newUser);

                    redirect = "/register?success";
                }

            }

        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Countries> countries = DBManager.getAllCountries();
        request.setAttribute("countries", countries);
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
