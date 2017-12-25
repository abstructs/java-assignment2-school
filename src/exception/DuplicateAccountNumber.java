package exception;

public class DuplicateAccountNumber extends Exception{
    public DuplicateAccountNumber(String message) {
        super(message);
    }
}