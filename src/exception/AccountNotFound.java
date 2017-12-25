package exception;

public class AccountNotFound extends Exception{
    public AccountNotFound(String message) {
        super(message);
    }
    public AccountNotFound() { super("Account not found!"); }
}