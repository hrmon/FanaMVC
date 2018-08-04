package model;

public abstract class Transaction implements ITransaction {
	private final IBankAccount sourceAccount;
	private final long value;

	public Transaction(IBankAccount sourceAccount, long value) {
		super();
		this.sourceAccount = sourceAccount;
		this.value = value;
	}
}
