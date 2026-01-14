package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super("invalid user credentials");
    }
}
