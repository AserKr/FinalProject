package hi.HBV202G;

public class UserOrBookDoesNotExistException extends Exception{
    public UserOrBookDoesNotExistException(String message) {
        super(message);
    }
}
