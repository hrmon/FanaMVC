package contoroler;

import model.User;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownServiceException;


@WebServlet("/getuser")
public class GetUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json = this.getUser();
        PrintWriter writer = response.getWriter();
        writer.write(getUser().toJSONString());
        writer.flush();
        writer.close();
    }

    private JSONObject getUser() {
        return User.getUsers();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
