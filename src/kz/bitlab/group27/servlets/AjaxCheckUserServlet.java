package kz.bitlab.group27.servlets;

import com.google.gson.Gson;
import kz.bitlab.group27.db.DBManager;
import kz.bitlab.group27.db.Users;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(value = "/ajax_check_user")
public class AjaxCheckUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");

        Users checkUser = DBManager.getUserByEmail(email);

        String message = "User with email \""+email+"\" doesn't exist !!!";
        String status = "error";

        if(checkUser!=null){

            message = "Incorrect password for user \""+email+"\" !!!";

            if(checkUser.getPassword().equals(DigestUtils.sha1Hex(password))){

                message = "Okay";
                status = "ok";

            }

        }

        HashMap<String, String> result = new HashMap<>();
        result.put("status", status);
        result.put("message", message);

        Gson gson = new Gson();
        out.print(gson.toJson(result));

    }
}
