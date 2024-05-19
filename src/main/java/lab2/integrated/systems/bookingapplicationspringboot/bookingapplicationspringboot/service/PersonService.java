package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service;


import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Person;

public interface PersonService{

    Person register(String username, String email, String password, String repeatPassword);

    Person login(String email, String password);

    Person findByUsername(String username);
}
