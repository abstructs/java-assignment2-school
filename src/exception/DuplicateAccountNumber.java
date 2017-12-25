package exception;

public class DuplicateAccountNumber extends Exception{
    public DuplicateAccountNumber(String message) {
        super(message);
    }
    public DuplicateAccountNumber() { super("That account number is already in use! Please try a different one."); }
}