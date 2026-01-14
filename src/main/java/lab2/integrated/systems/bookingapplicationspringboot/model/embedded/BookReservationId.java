package lab2.integrated.systems.bookingapplicationspringboot.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;



@Embeddable
@Data
public class BookReservationId implements Serializable {

    @Column(name = "bookListId")
    private Long bookListId;

    @Column(name = "reservationId")
    private Long reservationId;

    public BookReservationId(Long bookListId, Long reservationId) {
        this.bookListId = bookListId;
        this.reservationId = reservationId;
    }

    public BookReservationId() {

    }
}
