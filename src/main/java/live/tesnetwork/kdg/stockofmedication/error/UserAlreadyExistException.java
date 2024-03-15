package live.tesnetwork.kdg.stockofmedication.error;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException() {
        super("This user already exists. Please choose another username.");
    }
}
