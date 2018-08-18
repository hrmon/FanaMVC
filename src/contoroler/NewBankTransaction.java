package contoroler;

import model.BankAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newbanktransaction")
public class NewBankTransaction extends HttpServlet implements ITransaction {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long sourceAccountNumber = (long) request.getSession().getAttribute("accountnumber");
        String t = request.getParameter("destinationaccountnumber");
        Long destinationaccountnumber = (t.equals("") ? null : Long.parseLong(t));
        BankAccount sourceAccount = BankAccount.getAccount(sourceAccountNumber);
        t = request.getParameter("value");
        Long value = (t.equals("") ? null : Long.parseLong(t));
        if (value == null) {
            request.getSession().setAttribute("sourcefund", getFund(sourceAccount));
        } else if (destinationaccountnumber == null) {
            if (value < 0) {
                withrow(sourceAccount, -1*value);
                request.getSession().setAttribute("sourcefund", getFund(sourceAccount));
            } else {
                deposit(sourceAccount, value);
                request.getSession().setAttribute("sourcefund", getFund(sourceAccount));
            }
        } else {
            BankAccount destinationAccount = BankAccount.getAccount(destinationaccountnumber);
            transfer(sourceAccount, destinationAccount, value);
            request.getSession().setAttribute("sourcefund", getFund(sourceAccount));
            request.getSession().setAttribute("destinyfund", getFund(destinationAccount));
        }
        response.sendRedirect("index.jsp");
    }

    public void deposit(BankAccount source, long value) {
        source.deposit(value);
    }

    public long getFund(BankAccount source) {
        return source.getFund();
    }

    public void transfer(BankAccount source, BankAccount desteny, long value) {
        source.transfer(desteny ,value);
    }

    public void withrow(BankAccount source, long value) {
        source.withrow(value);
    }
}
