package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;







@Data
@Entity
@Table(name = "BookingApplicationUser")
public class User  {


    public User() {

    }

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Person person;


    @OneToOne
    private BookList bookList;


    @OneToMany(mappedBy = "bookingApplicationUser")
    private List<Reservation> reservations;

    public User(Person person) {
        this.person = person;
    }



}

