package kz.bitlab.group27.servlets;

import kz.bitlab.group27.db.DBManager;
import kz.bitlab.group27.db.Users;
import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updateprofile")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users user = (Users)request.getSession().getAttribute("USER");

        if(user!=null){

            String fullName = request.getParameter("full_name");
            user.setFullName(StringEscapeUtils.escapeHtml(fullName));
            if(DBManager.updateUserProfile(user)){
                request.getSession().setAttribute("USER", user);
            }

            response.sendRedirect("/profile");

        }else{
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
