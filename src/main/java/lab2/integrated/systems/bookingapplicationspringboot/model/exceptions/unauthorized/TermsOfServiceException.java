package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.unauthorized;

public class TermsOfServiceException extends RuntimeException{
    public TermsOfServiceException() {
        super("Terms Of Service Exception ");
    }
}
