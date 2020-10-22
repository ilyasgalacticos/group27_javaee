package kz.bitlab.group27.servlets;

import com.google.gson.Gson;
import kz.bitlab.group27.db.DBManager;
import kz.bitlab.group27.db.Hotels;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String key = request.getParameter("key");

        int priceFrom = -1;
        int priceTo = -1;

        int starsFrom = -1;
        int starsTo = -1;

        try{

            String priceFromText = request.getParameter("price_from");
            priceFrom = Integer.parseInt(priceFromText);

        }catch (Exception e){
        }

        try{

            String priceToText = request.getParameter("price_to");
            priceTo = Integer.parseInt(priceToText);

        }catch (Exception e){
        }

        try{

            String starsFromText = request.getParameter("stars_from");
            starsFrom = Integer.parseInt(starsFromText);

        }catch (Exception e){
        }

        try{

            String starsToText = request.getParameter("stars_to");
            starsTo = Integer.parseInt(starsToText);

        }catch (Exception e){
        }

        ArrayList<Hotels> hotels = DBManager.searchHotels(key, priceFrom, priceTo,starsFrom,starsTo);

        request.setAttribute("hotels", hotels);
        request.getRequestDispatcher("/search.jsp").forward(request, response);

    }
}
