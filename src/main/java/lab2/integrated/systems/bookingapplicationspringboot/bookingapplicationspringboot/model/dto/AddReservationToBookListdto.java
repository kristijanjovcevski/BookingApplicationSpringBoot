package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.dto;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Reservation;
import lombok.Data;


@Data
public class AddReservationToBookListdto {

    private Long reservationId;

    private Reservation reservation;


    private int pricePerNight;


    public AddReservationToBookListdto(Long reservationId, Reservation reservation, int pricePerNight) {
        this.reservationId = reservationId;
        this.reservation = reservation;
        this.pricePerNight = pricePerNight;
    }
}
