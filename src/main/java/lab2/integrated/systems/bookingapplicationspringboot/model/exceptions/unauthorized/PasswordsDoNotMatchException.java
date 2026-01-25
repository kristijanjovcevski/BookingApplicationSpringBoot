package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.unauthorized;

public class PasswordsDoNotMatchException extends RuntimeException{


    public PasswordsDoNotMatchException() {
        super("Passwords do not match");
    }
}
