package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.conflict;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException() {
        super("Username Already Exists");
    }
}
