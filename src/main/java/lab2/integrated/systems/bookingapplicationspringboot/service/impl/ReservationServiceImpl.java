package lab2.integrated.systems.bookingapplicationspringboot.service.impl;

import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.model.Reservation;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.notFound.ApartmentNotFoundException;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.notFound.ReservationNotFoundException;
import lab2.integrated.systems.bookingapplicationspringboot.repository.ApartmentRepository;
import lab2.integrated.systems.bookingapplicationspringboot.repository.ReservationRepository;
import lab2.integrated.systems.bookingapplicationspringboot.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ApartmentRepository apartmentRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ApartmentRepository apartmentRepository) {
        this.reservationRepository = reservationRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Reservation create(LocalDateTime localDateTime, Long apartmentId) {

        Apartment apartment = this.apartmentRepository.findById(apartmentId).orElseThrow(() -> new ApartmentNotFoundException(apartmentId));

        return this.reservationRepository.save(new Reservation(localDateTime, apartment));
    }

    @Override
    public Reservation findById(Long id) {
        return this.reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }
}
