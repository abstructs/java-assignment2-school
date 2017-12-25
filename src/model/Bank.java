package model;
import exception.DuplicateAccountNumber;
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
    // REQUIRES: accountNum must be unique
    public boolean addAccount(long accNum, double bal, String own) throws DuplicateAccountNumber {
        if(this.findAccount(accNum) != -1) {
            throw new DuplicateAccountNumber("Account number already in use.");
        }

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
        for(int i = 0; i < numAccounts; i++) {
            stringBuilder.append(accountList[i].toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    // EFFECTS: returns the location(index) of the account where the accountNumber equals accNum
    //          returns -1 if the object does not exist
    public int findAccount(long accNum) {
        int index = 0;
        for(int i = 0; i < numAccounts; i++) {
            if(accountList[i].getAccountNum() == accNum) {
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
        try {
            accountList[this.findAccount(accNum)].deposit(amount);
        } catch(ArrayIndexOutOfBoundsException e) {}
    }

    // EFFECTS: finds the account with the matching account number
    //          in accountList and if it exists, withdraws the
    //          amount from the balance if possible and returns a boolean
    //          corresponding to whether the withdraw was successful
    public boolean withdrawAccount(long accNum, double amount) {
        try {
            return accountList[this.findAccount(accNum)].withdraw(amount);
        } catch(ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    // EFFECTS: finds both of the accounts with the matching account
    //          numbers in accountList and if they exist,
    //          attempts to perform the transfer.
    public boolean transfer(long accNumFrom,long accNumTo, double amount) {
        try {
            Account accFrom = accountList[this.findAccount(accNumFrom)];
            Account accTo = accountList[this.findAccount(accNumTo)];
            return accFrom.transfer(accTo, amount);
        } catch(ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
