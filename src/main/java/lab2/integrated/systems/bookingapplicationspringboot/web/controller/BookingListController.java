package lab2.integrated.systems.bookingapplicationspringboot.web.controller;

import lab2.integrated.systems.bookingapplicationspringboot.model.BookList;
import lab2.integrated.systems.bookingapplicationspringboot.model.BookReservation;
import lab2.integrated.systems.bookingapplicationspringboot.model.dto.ShowReservationsToBookListdto;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.BookListNotFoundException;
import lab2.integrated.systems.bookingapplicationspringboot.repository.BookListRepository;
import lab2.integrated.systems.bookingapplicationspringboot.repository.BookReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookingListController {

    private final BookListRepository bookListRepository;
    private final BookReservationRepository bookReservationRepository;



    public BookingListController(BookListRepository bookListRepository, BookReservationRepository bookReservationRepository) {
        this.bookListRepository = bookListRepository;
        this.bookReservationRepository = bookReservationRepository;

    }

    @GetMapping("/bookings")

    public String getBookingList(Model model){

        BookList bookList = this.bookListRepository.findById(102L).orElseThrow(() -> new BookListNotFoundException(102L));

        List<BookReservation> bookReservations = bookList.getBookReservations();
        double totalPrice = 0.0;

        for (BookReservation b : bookReservations){
            totalPrice += b.getPricePerNight();
        }

        ShowReservationsToBookListdto dto = new ShowReservationsToBookListdto(bookReservations,totalPrice);


        model.addAttribute("dtoListReservations", dto);

        return "bookingList";
    }

    @PostMapping("/clear")
    public String bookNow(){

        BookList bookList = this.bookListRepository.findById(102L).orElseThrow(() -> new BookListNotFoundException(102L));

        List<BookReservation> bookReservations = bookList.getBookReservations();

        /*for (BookReservation bookReservation : bookReservations){
            BookReservationId bookReservationId = bookReservation.getId();

            this.bookReservationRepository.deleteById(bookReservationId);
        }*/

        this.bookReservationRepository.deleteAll(bookReservations);







        return "redirect:/bookings";
    }
}
