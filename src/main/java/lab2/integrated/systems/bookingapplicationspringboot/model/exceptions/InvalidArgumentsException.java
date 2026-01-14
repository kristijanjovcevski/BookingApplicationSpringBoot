package lab2.integrated.systems.bookingapplicationspringboot.model.exceptions;

public class InvalidArgumentsException extends RuntimeException{

    public InvalidArgumentsException() {
        super("Invalid arguments");
    }
}
