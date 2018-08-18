package contoroler;

import model.User;

public interface IBankAccount {
	public void closeBankAccount(String userName);
	public void closeBankAccount(User user);
	public long newBankAccount(String userName);

	public long newBankAccount(User user);

}
