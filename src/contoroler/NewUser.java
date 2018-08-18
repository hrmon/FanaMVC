package contoroler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/newuser")
public class NewUser extends HttpServlet implements IUserControler {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUser() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String userName = request.getParameter("username");
        String pass = request.getParameter("pass");
        this.newUser(userName, pass);
        request.getSession().setAttribute("username", userName);
        response.sendRedirect("index.jsp");
    }

    @Override
    public  User getUser(String name, String pass) {
        return User.getUser(name);
    }

    @Override
    public  void newUser(String name, String pass) {
        User.addUser(name, pass);
    }

}
