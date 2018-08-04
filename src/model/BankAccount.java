package model;

import java.util.Random;

public class BankAccount implements IBankAccount {
	private static Random rnd = new Random(0);

	private static final long newAcconutNumber() {
		return BankAccount.rnd.nextLong();
	}

	private final IUser user;
	private final long accountNumber;

	public BankAccount(String name) {
		this(new User(name));
	}

	public BankAccount(IUser user) {
		super();
		this.user = user;
		this.accountNumber = BankAccount.newAcconutNumber();
	}
}
