package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException() {
        super("Email Already Exists ");
    }
}
