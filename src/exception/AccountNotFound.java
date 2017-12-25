package exception;

public class AccountNotFound extends Exception{
    public AccountNotFound(String message) {
        super(message);
    }
    public AccountNotFound() { super("An account with that account number doesn't exist!"); }
}