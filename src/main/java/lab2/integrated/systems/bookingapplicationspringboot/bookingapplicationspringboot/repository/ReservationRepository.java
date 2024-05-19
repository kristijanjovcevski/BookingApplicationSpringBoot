package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    void deleteByApartmentId (Long apartmentId);
}
