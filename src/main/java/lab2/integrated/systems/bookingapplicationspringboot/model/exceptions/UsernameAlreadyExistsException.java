package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException() {
        super("Username Already Exists");
    }
}
