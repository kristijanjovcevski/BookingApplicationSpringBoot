package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity

public class BookList {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User customer;

    /*@ManyToMany
    @JoinTable(
            name = "book_reservation",
            joinColumns = @JoinColumn(name = "bookListId"),
            inverseJoinColumns = @JoinColumn(name = "reservationId")

    )*/
    @OneToMany(mappedBy = "bookList", fetch = FetchType.EAGER)
    private List<BookReservation> bookReservations;


    public BookList(User customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "BookList{id=" + id + "}";
    }




}