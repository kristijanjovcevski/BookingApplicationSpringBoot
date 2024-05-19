package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.dto;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.BookReservation;
import lombok.Data;

import java.util.List;

@Data
public class ShowReservationsToBookListdto {

    private List<BookReservation> reservations;

    private double totalPrice;


    public ShowReservationsToBookListdto(List<BookReservation> reservations, double totalPrice) {
        this.reservations = reservations;
        this.totalPrice = totalPrice;
    }
}
