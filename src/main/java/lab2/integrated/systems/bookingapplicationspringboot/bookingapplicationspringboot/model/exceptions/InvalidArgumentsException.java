package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.exceptions;

public class InvalidArgumentsException extends RuntimeException{

    public InvalidArgumentsException() {
        super("Invalid arguments");
    }
}
