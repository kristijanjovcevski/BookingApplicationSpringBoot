package lab2.integrated.systems.bookingapplicationspringboot.service.impl;

import lab2.integrated.systems.bookingapplicationspringboot.model.BookList;
import lab2.integrated.systems.bookingapplicationspringboot.model.Person;
import lab2.integrated.systems.bookingapplicationspringboot.model.User;
import lab2.integrated.systems.bookingapplicationspringboot.model.enums.Role;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.EmailAlreadyExistsException;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.InvalidArgumentsException;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.PasswordsDoNotMatchException;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.UsernameAlreadyExistsException;
import lab2.integrated.systems.bookingapplicationspringboot.repository.BookListRepository;
import lab2.integrated.systems.bookingapplicationspringboot.repository.PersonRepository;
import lab2.integrated.systems.bookingapplicationspringboot.repository.UserRepository;
import lab2.integrated.systems.bookingapplicationspringboot.service.PersonService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {


    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final BookListRepository bookListRepository;

    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository, PasswordEncoder passwordEncoder,
                             BookListRepository bookListRepository ) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookListRepository = bookListRepository;
    }

    @Override
    public Person register(String username, String email, String password,
                           String repeatPassword) {

        if ("".equals(username) || "".equals(email) || "".equals(password)){
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }
        if (this.personRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException();
        }
        if (this.personRepository.findByEmail(email).isPresent()){
            throw new EmailAlreadyExistsException();
        }

        Person person = this.personRepository.save(new Person(username,email,passwordEncoder.encode(password), Role.ROLE_User));
        User user =  this.userRepository.save(new User(person));

        BookList bookList = new BookList(user);

        BookList tmp = this.bookListRepository.save(bookList);

        user.setBookList(tmp);

        this.userRepository.save(user);

        return person;

    }

    @Override

    public Person login(String email, String password) {

        if (email.isEmpty() || password.isEmpty())
            throw new InvalidArgumentsException();

        Person person = this.personRepository.findByEmail(email).orElseThrow(InvalidArgumentsException::new);


        String hashedPasswordFromDatabase = person.getPassword();

        if (passwordEncoder.matches(password, hashedPasswordFromDatabase)) {
            return person;
        }

        else{
            throw new InvalidArgumentsException();
        }

    }

    @Override
    public Person findByUsername(String username) {

        return this.personRepository.findByUsername(username).orElseThrow(null);
    }

}
