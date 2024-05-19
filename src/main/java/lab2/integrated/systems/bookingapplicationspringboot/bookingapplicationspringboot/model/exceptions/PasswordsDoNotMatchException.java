package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{


    public PasswordsDoNotMatchException() {
        super("Passwords do not match");
    }
}
