package contoroler;

import model.User;

public interface IBankAccount {
	public void closeBankAccount();

	public long newBankAccount(String userName);

	public long newBankAccount(User user);

}
