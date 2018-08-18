package model;

import model.interfaces.IBankAccount;
import model.interfaces.ITransaction;

public class Transaction implements ITransaction {
	private final IBankAccount sourceAccount, destinationAccount;
	private final long value;

	public Transaction(IBankAccount sourceAccount, IBankAccount destinationAccount, long value) {
		super();
		this.sourceAccount = sourceAccount;
		this.value = value;
		this.destinationAccount = destinationAccount;
	}

	public Transaction(IBankAccount sourceAccount, long value) {
		super();
		this.sourceAccount = sourceAccount;
		this.value = value;
		this.destinationAccount = null;
	}

}
