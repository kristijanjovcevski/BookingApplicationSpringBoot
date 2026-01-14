package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ApartmentNotFoundException extends RuntimeException{
    public ApartmentNotFoundException(Long id) {
        super(String.format("Apartment with name %d not found", id));
    }
}
