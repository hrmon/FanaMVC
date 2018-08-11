package contoroler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bank;
import model.User;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewAccount extends HttpServlet implements IBankAccount {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void closeBankAccount() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("userName");
		request.getSession().getAttribute("pass");
		request.setAttribute("accountNumber", newBankAccount(userName));
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public long newBankAccount(String userName) {
		return newBankAccount(new Bank().getUser(userName));

	}

	@Override
	public long newBankAccount(User user) {
		return new Bank().newAcount(user);
	}

}
