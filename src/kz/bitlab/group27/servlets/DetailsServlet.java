package kz.bitlab.group27.servlets;

import kz.bitlab.group27.db.Comments;
import kz.bitlab.group27.db.DBManager;
import kz.bitlab.group27.db.Hotels;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hotels hotel = null;
        try{

            Long id = Long.parseLong(request.getParameter("id"));
            hotel = DBManager.getHotel(id);

        }catch (Exception e){

        }

        if(hotel!=null){

            request.setAttribute("hotel", hotel);

            ArrayList<Comments> comments = DBManager.getAllComments(hotel.getId());
            request.setAttribute("comments", comments);

            request.getRequestDispatcher("/details.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }
    }
}
