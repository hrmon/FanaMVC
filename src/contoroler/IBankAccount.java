package contoroler;

import model.User;

public interface IBankAccount {
	public void closeBankAccount();

	public void newBankAccount(String userName);

	public void newBankAccount(User user);

}
