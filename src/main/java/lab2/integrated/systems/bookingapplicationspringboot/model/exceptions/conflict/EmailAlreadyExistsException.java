package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.conflict;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException() {
        super("Email Already Exists ");
    }
}
