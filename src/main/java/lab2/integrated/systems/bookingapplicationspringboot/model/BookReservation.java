package lab2.integrated.systems.bookingapplicationspringboot.model;

import jakarta.persistence.*;
import lab2.integrated.systems.bookingapplicationspringboot.model.embedded.BookReservationId;
import lombok.Data;

@Entity
@Data
public class BookReservation {

    @EmbeddedId
    private BookReservationId id;

    @ManyToOne
    @JoinColumn(name = "bookListId", insertable = false, updatable = false)
    private BookList bookList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservationId", insertable = false, updatable = false)
    private Reservation reservation;

    @Column(name = "quantity")
    private double pricePerNight;

    public BookReservation(BookReservationId id, BookList bookList, Reservation reservation, double pricePerNight) {
        this.id = id;
        this.bookList = bookList;
        this.reservation = reservation;
        this.pricePerNight = pricePerNight;
    }

    public BookReservation() {

    }

    @Override
    public String toString() {
        return "BookReservation{" +
                "id=" + id +

                ", reservation=" + reservation +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
