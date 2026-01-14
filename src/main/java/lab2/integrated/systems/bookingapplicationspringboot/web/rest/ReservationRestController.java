package lab2.integrated.systems.bookingapplicationspringboot.web.rest;

import lab2.integrated.systems.bookingapplicationspringboot.model.Reservation;
import lab2.integrated.systems.bookingapplicationspringboot.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> findAll(){
        return reservationService.getAllReservations();
    }
}
