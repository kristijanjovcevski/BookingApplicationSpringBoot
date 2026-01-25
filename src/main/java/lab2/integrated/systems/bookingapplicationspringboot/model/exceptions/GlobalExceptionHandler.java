package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions;

import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.notFound.ApartmentNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    /*@ExceptionHandler(ApartmentNotFoundException.class)
    public String handleApartmentNotFound(ApartmentNotFoundException ex, Model model) {
        model.addAttribute("apartmentId", ex.getMessage());
        return "add";
    }*/
}
