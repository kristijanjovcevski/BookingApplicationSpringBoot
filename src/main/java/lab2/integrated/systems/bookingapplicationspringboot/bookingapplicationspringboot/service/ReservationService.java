package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();

    Reservation create(LocalDateTime localDateTime, Long apartmentId);

    Reservation findById(Long id);

}
