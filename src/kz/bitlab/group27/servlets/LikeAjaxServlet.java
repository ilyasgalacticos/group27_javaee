package kz.bitlab.group27.servlets;

import kz.bitlab.group27.db.DBManager;
import kz.bitlab.group27.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/ajaxlike")
public class LikeAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users user = (Users)request.getSession().getAttribute("USER");
        int likes = -1;

        if(user!=null){

            Long hotelId = Long.parseLong(request.getParameter("hotel_id"));
            likes = DBManager.toLikeHotel(hotelId, user.getId());

        }

        PrintWriter out = response.getWriter();
        out.print(likes);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
