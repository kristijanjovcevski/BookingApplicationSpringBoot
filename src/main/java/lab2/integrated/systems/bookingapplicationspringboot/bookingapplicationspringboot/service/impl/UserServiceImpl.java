package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.impl;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository.BookListRepository;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository.UserRepository;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final BookListRepository bookListRepository;


    public UserServiceImpl(UserRepository userRepository, BookListRepository bookListRepository) {
        this.userRepository = userRepository;
        this.bookListRepository = bookListRepository;
    }

    /*@Override
    public User register(String email, String firstName, String lastName, String address, String password) {



        BookList bookList = new BookList();

        List<Reservation> reservations = new ArrayList<>();


        this.bookListRepository.save(bookList);

        User user  = new User(email, firstName,lastName,address,password, bookList , reservations);

        User savedUser = this.userRepository.save(user);

        bookList.setCustomer(savedUser);

        this.bookListRepository.save(bookList);

        return savedUser;
    }*/

}
