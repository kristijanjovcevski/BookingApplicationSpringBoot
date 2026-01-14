package lab2.integrated.systems.bookingapplicationspringboot.repository;

import lab2.integrated.systems.bookingapplicationspringboot.model.BookReservation;
import lab2.integrated.systems.bookingapplicationspringboot.model.embedded.BookReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReservationRepository extends JpaRepository<BookReservation, BookReservationId> {

}
