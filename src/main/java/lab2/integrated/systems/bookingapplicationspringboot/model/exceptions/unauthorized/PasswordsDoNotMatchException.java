package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.unauthorized;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PasswordsDoNotMatchException extends RuntimeException{


    public PasswordsDoNotMatchException() {
        super("Passwords do not match");
    }
}
