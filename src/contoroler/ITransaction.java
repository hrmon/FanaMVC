package contoroler;

import model.BankAccount;

public interface ITransaction {

	public void deposit(BankAccount source, long value);

	public void getFund(BankAccount source);

	public void transfer(BankAccount source, BankAccount desteny, long value);

	public void withrow(BankAccount source, long value);

}
