package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException() {
        super("Username Already Exists");
    }
}
