package lab2.integrated.systems.bookingapplicationspringboot.controller;

import lab2.integrated.systems.bookingapplicationspringboot.model.*;
import lab2.integrated.systems.bookingapplicationspringboot.model.embedded.BookReservationId;
import lab2.integrated.systems.bookingapplicationspringboot.model.enums.Role;
import lab2.integrated.systems.bookingapplicationspringboot.repository.BookListRepository;
import lab2.integrated.systems.bookingapplicationspringboot.repository.BookReservationRepository;
import lab2.integrated.systems.bookingapplicationspringboot.web.controller.BookingListController;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingListController.class)
public class BookingListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookListRepository bookListRepository;

    @MockBean
    private BookReservationRepository bookReservationRepository;

    private BookList bookList;

    @BeforeEach
    void setUp() {
        Apartment apartment = apartment();
        Reservation reservation = reservation(1L, apartment, LocalDate.of(2026, 8, 1), LocalTime.of(12, 0, 0));
        Reservation reservation2 = reservation(2L, apartment, LocalDate.of(2026, 6, 27), LocalTime.of(9, 0, 0));
        bookList = bookListWithCustomer();
        BookReservation bookReservation = bookReservation(bookList, reservation);
        BookReservation bookReservation2 = bookReservation(bookList, reservation2);
        bookList.setBookReservations(List.of(bookReservation, bookReservation2));
        Mockito.when(bookListRepository.findById(anyLong())).thenReturn(Optional.of(bookList));
    }

    @Test
    void getBookingList() throws Exception {
        double totalPrice = bookList.getBookReservations()
                .stream()
                .mapToDouble(BookReservation::getPricePerNight)
                .sum();

        mockMvc.perform(get("/bookings"))
                .andExpectAll(status().isOk(),
                        view().name("bookingList"),
                        model().attributeExists("dtoListReservations"),
                        model().attribute("dtoListReservations", hasProperty("totalPrice", is(totalPrice))));

        Mockito.verify(bookListRepository).findById(anyLong());
    }

    @Test
    void bookNow() throws Exception {
        mockMvc.perform(post("/clear"))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("/bookings"));

        Mockito.verify(bookListRepository).findById(anyLong());
        Mockito.verify(bookReservationRepository).deleteAll(bookList.getBookReservations());
    }

    private Apartment apartment() {
        Apartment apartment = new Apartment("Goodys", "Ohrid", "Very good", 400, 4.5);
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
