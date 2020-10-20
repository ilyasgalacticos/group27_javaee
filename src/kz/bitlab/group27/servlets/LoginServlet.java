package kz.bitlab.group27.servlets;

import kz.bitlab.group27.db.DBManager;
import kz.bitlab.group27.db.Users;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String redirect = "/login?usererror&email="+(email!=null?email:"");

        Users user = DBManager.getUserByEmail(email);

        if(user!=null){

            redirect = "/login?passworderror&email="+(email!=null?email:"");

            if(user.getPassword().equals(DigestUtils.sha1Hex(password))){

                HttpSession session = request.getSession();
                session.setAttribute("USER", user);
                redirect = "/";

            }

        }

        response.sendRedirect(redirect);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
