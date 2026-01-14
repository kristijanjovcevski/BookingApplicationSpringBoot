package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookListNotFoundException extends RuntimeException{

    public BookListNotFoundException(Long id) {

        super(String.format("BookList with name %d not found", id));
    }
}
