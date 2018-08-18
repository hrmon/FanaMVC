package contoroler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BankAccount;
import model.User;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/newaccount")
public class NewAccount extends HttpServlet implements IBankAccount {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewAccount() {
		super();
	}

	@Override
	public void closeBankAccount(User user) {
		// TODO Auto-generated method stub

	}
	@Override
	public void closeBankAccount(String userName) {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName =(String) request.getSession().getAttribute("username");
		request.getSession().setAttribute("accountnumber", newBankAccount(userName));
		response.sendRedirect("index.jsp");
	}

	@Override
	public long newBankAccount(String userName) {
		return BankAccount.newAccount(User.getUser(userName));

	}

	@Override
	public long newBankAccount(User user) {
		return BankAccount.newAccount(user);
	}

}
