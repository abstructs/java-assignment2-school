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
        this.maxAccount = 10000;
        this.accountList = new Account[this.maxAccount];
        this.numAccounts = 0;
    }


    // EFFECTS: creates and adds an account  to the array
    public boolean addAccount(long accNum, double bal, String own) {
        if(numAccounts + 1 < maxAccount) {
            Account acc = new Account(accNum, bal, own);
            this.accountList[numAccounts++] = acc;
            return true;
        }
        return false;
    }

    // EFFECTS: returns a string with all the account information for each account in the array
    public String printAccounts() {
        // string builder provides faster implementation for concatenation
        // because String is immutable, each iteration String would be completely
        // copied over. See https://stackoverflow.com/questions/5234147/why-stringbuilder-when-there-is-string
        // for more.
        StringBuilder stringBuilder = new StringBuilder();
        for(Account a : accountList) {
            stringBuilder.append(a.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    // EFFECTS: returns the location(index) of the account where the accountNumber equals accNum
    //          returns -1 if the object does not exist
    public int findAccount(long accNum) {
        int index = 0;
        for(Account acc : accountList) {
            if(acc.getAccountNum() == accNum) {
                return index;
            }
            index++;
        }

        return -1;
    }

    // EFFECTS:  finds the account with the matching
    //           account number in accountList and if it exists,
    //           adds the amount to the balance
    public void depositAccount(long accNum, double amount) {
        for(Account acc : accountList) {
            if(acc.getAccountNum() == accNum) {
                acc.deposit(amount);
            }
        }
    }

    // EFFECTS: finds the account with the matching account number
    //          in accountList and if it exists, withdraws the
    //          amount from the balance if possible and returns a boolean
    //          corresponding to whether the withdraw was successful
    public boolean withdrawAccount(long accNum, double amount) {
        for(Account acc : accountList) {
            if(acc.getAccountNum() == accNum) {
                return acc.withdraw(amount);
            }
        }

        return false;
    }

    // EFFECTS: finds both of the accounts with the matching account
    //          numbers in accountList and if they exist,
    //          attempts to perform the transfer.
    public boolean transfer(long accNumFrom,long accNumTo, double amount) {
        // initialized to null so we can check if they've been set in the loop
        Account accFrom = null;
        Account accTo = null;

        for (Account acc : accountList) {
            if (acc.getAccountNum() == accNumFrom) {
                accFrom = acc;
            } else if (acc.getAccountNum() == accNumTo) {
                accTo = acc;
            }
        }

        // will short circuit and return false if either accFrom or accTo are null
        return accFrom != null && accTo != null && accFrom.transfer(accTo, amount);
    }
}
