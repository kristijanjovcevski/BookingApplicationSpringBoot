package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TermsOfServiceException extends RuntimeException{
    public TermsOfServiceException() {
        super("Terms Of Service Exception ");
    }
}
