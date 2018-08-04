package model;

public abstract class TransferTransaction extends Transaction {
	private final IBankAccount destinationAccount;

	public TransferTransaction(IBankAccount sourceAccount, IBankAccount destinationAccount, long value) {
		super(sourceAccount, value);
		this.destinationAccount = destinationAccount;
	}

}
