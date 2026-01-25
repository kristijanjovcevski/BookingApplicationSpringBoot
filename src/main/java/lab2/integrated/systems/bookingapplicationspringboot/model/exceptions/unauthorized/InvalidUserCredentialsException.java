package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.unauthorized;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super("invalid user credentials");
    }
}
