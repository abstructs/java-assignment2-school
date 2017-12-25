package model;

// Andrew Wilson - 101069860

public class Account {
    // the account number
    private long accountNum;

    //the balance on the account
    private double balance;

    // a String representing the owner’s full name
    private String owner;

    public Account(long accountNum, double balance, String owner) {
        this.accountNum = accountNum;
        this.balance = balance;
        this.owner = owner;
    }

    public long getAccountNum() {
        return accountNum;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    // EFFECTS: reduces the balance by amount once there are sufficient funds
    //          if there are sufficient funds perform the transaction and return Boolean
    // MODIFIES: balance
    // REQUIRES: account must hold more than the withdraw amount
    public boolean withdraw(double amount) {
        return false;
    }

    // EFFECTS: increases the balance by amount
    // MODIFIES: balance
    public boolean deposit(double amount) {
        return false;
    }

    // EFFECTS: transfers money from one account to another.
    //          if there are sufficient funds performs the transaction and returns a boolean
    // REQUIRES: there must be sufficient money in sending account
    // MODIFIES: balance
    public boolean transfer(Account acc, long amount) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
