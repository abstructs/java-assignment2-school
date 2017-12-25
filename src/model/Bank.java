package model;

// Andrew Wilson - 101069860

public class Bank {

    // an array of type Account of size ten(1000)
    private Account[] accountList;

    // an integer indicating the number of accounts in the array
    private int numAccounts;

    // an integer representing the maximum number of accounts allowed
    private int maxAccount;

    public Bank() {

    }


    // EFFECTS: creates and adds an account  to the array
    public boolean addAccount(long accNum, double bal, String own) {
        return false;
    }

    // EFFECTS: returns a string with all the account information for each account in the array
    public String printAccounts() {
        return "";
    }

    // EFFECTS: returns the location(index) of the account where the accountNumber equals accNum
    public int findAccount(long accNum) {
        return 0;
    }

    // EFFECTS:  finds the account with the matching
    //           account number in accountList and if it exists,
    //           adds the amount to the balance
    public void depositAccount(long accNum, double amount) {

    }

    // EFFECTS: finds the account with the matching account number
    //          in accountList and if it exists, withdraws the
    //          amount from the balance if possible
    public boolean withdrawAccount(long accNum, double amount) {
        return false;
    }

    // EFFECTS: finds both of the accounts with the matching account
    //          numbers in accountList and if they exist,
    //          attempts to perform the transfer.
    public boolean transfer(long accNumFrom,long accNumTo, double amount) {
        return false;
    }
}
