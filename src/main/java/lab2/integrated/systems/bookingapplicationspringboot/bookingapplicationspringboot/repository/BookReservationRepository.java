package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.BookReservation;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.embedded.BookReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReservationRepository extends JpaRepository<BookReservation, BookReservationId> {

    List<BookReservation> findAllByBookListId(Long id);
}
