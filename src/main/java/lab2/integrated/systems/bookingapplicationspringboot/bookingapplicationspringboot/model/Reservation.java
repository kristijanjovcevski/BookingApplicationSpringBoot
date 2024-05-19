package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Reservation {


    @Id
    @GeneratedValue //auto strategy
    private Long id;

    //private Long apartmentId;
    //anotaciive avtomatski kreiraat kluc

    public LocalDateTime localDateTime;

    @OneToOne
    private Apartment apartment;

    @ManyToOne
    private User bookingApplicationUser;

    //@ManyToMany(mappedBy = "bookReservations")
    @OneToMany(mappedBy = "reservation")
    private List<BookReservation> bookLists;

    public Reservation(LocalDateTime localDateTime, Apartment apartment) {
        this.localDateTime = localDateTime;
        this.apartment = apartment;
    }

    public Reservation() {

    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", localDateTime=" + localDateTime +
                ", apartment=" + apartment +
                ", bookingApplicationUser=" + bookingApplicationUser +
                // Exclude bookLists field
                '}';
    }
}