package lab2.integrated.systems.bookingapplicationspringboot.controller;

import lab2.integrated.systems.bookingapplicationspringboot.model.*;
import lab2.integrated.systems.bookingapplicationspringboot.model.embedded.BookReservationId;
import lab2.integrated.systems.bookingapplicationspringboot.model.enums.Role;
import lab2.integrated.systems.bookingapplicationspringboot.repository.BookListRepository;
import lab2.integrated.systems.bookingapplicationspringboot.repository.BookReservationRepository;
import lab2.integrated.systems.bookingapplicationspringboot.service.ApartmentService;
import lab2.integrated.systems.bookingapplicationspringboot.service.ReservationService;
import lab2.integrated.systems.bookingapplicationspringboot.web.controller.ReservationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private ApartmentService apartmentService;

    @MockBean
    private BookListRepository bookListRepository;

    @MockBean
    private BookReservationRepository bookReservationRepository;

    private Apartment apartment;

    @BeforeEach
    void setUp() {
        apartment = apartment();
    }

    @Test
    void listReservations() throws Exception {
        LocalDateTime expectedDateTime = LocalDateTime.of(LocalDate.of(2026, 7, 1),
                LocalTime.of(12, 0, 0, 0));
        Reservation reservation = reservation(2L, apartment, expectedDateTime.toLocalDate(), expectedDateTime.toLocalTime());
        List<Reservation> reservationList = List.of(reservation);
        Mockito.when(reservationService.getAllReservations()).thenReturn(reservationList);

        mockMvc.perform(get("/reservations"))
                .andExpectAll(status().isOk(),
                        model().attributeExists("reservations"),
                        model().attribute("reservations", reservationList),
                        view().name("reservations"));

        Mockito.verify(reservationService).getAllReservations();
    }

    @Test
    void addReservationPage() throws Exception {
        List<Apartment> apartmentList = List.of(apartment());
        Mockito.when(apartmentService.getAllApartments()).thenReturn(apartmentList);

        mockMvc.perform(get("/add/reservation"))
                .andExpectAll(status().isOk(),
                        model().attributeExists("apartments"),
                        model().attribute("apartments", apartmentList),
                        view().name("create_reservation"));
        Mockito.verify(apartmentService).getAllApartments();
    }

    @Test
    void saveReservation() throws Exception {
        LocalDateTime expectedDateTime = LocalDateTime.of(LocalDate.of(2026, 7, 1),
                LocalTime.of(12, 0, 0, 0));
        Reservation reservation = reservation(2L, apartment, expectedDateTime.toLocalDate(), expectedDateTime.toLocalTime());
        Mockito.when(reservationService.create(expectedDateTime, apartment.getId())).thenReturn(reservation);

        mockMvc.perform(post("/add/reservation")
                        .param("localDateTime", "2026-07-01T12:00:00")
                        .param("id", "1"))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("/reservations"));
        Mockito.verify(reservationService).create(expectedDateTime, apartment.getId());
    }

    @Test
    void update() throws Exception {
        LocalDateTime expectedDateTime = LocalDateTime.of(LocalDate.of(2026, 7, 1),
                LocalTime.of(12, 0, 0, 0));
        Reservation reservation = reservation(2L, apartment, expectedDateTime.toLocalDate(), expectedDateTime.toLocalTime());
        BookList bookList = bookListWithCustomer();
        BookReservation bookReservation = bookReservation(bookList, reservation);
        Mockito.when(bookListRepository.findById(102L)).thenReturn(Optional.of(bookList));
        Mockito.when(reservationService.findById(2L)).thenReturn(reservation);
        Mockito.when(bookReservationRepository.save(bookReservation)).thenReturn(bookReservation);

        mockMvc.perform(post("/add/reservation/booklist/{id}", reservation.getId())
                        .param("pricePerNight", "" + bookReservation.getPricePerNight()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("/reservations"));

        Mockito.verify(bookListRepository).findById(102L);
        Mockito.verify(reservationService).findById(2L);
        Mockito.verify(bookReservationRepository).save(bookReservation);
    }

    private Apartment apartment() {
        Apartment apartment = new Apartment("Goodys", "New York", "Very good", 400, 4.5);
        apartment.setId(1L);
        return apartment;
    }

    private Reservation reservation(Long id, Apartment apartment, LocalDate date, LocalTime time) {
        Reservation reservation = new Reservation(LocalDateTime.of(date, time), apartment);
        reservation.setId(id);
        return reservation;
    }

    private BookList bookListWithCustomer() {
        Person person = new Person("johny bravo", "johny.bravo@hotmail.com", "bravisimo", Role.ROLE_User);
        person.setId(4L);
        User customer = new User(person);
        customer.setId(5L);
        BookList bookList = new BookList(customer);
        bookList.setId(6L);
        return bookList;
    }

    private BookReservation bookReservation(BookList bookList, Reservation reservation) {
        BookReservationId bookReservationId = new BookReservationId(bookList.getId(), reservation.getId());
        BookReservation bookReservation = new BookReservation(bookReservationId, bookList, reservation, reservation.getApartment().getPricePerNight());
        bookReservation.setId(bookReservationId);
        return bookReservation;
    }

}
