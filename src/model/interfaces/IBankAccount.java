package model.interfaces;

import model.BankAccount;

public interface IBankAccount {

    void deposit(long value);

    void withrow(long value);

    void transfer(BankAccount desteny, long value);
}