package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.web.controller;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.BookList;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.BookReservation;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Reservation;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.dto.AddReservationToBookListdto;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.embedded.BookReservationId;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.exceptions.BookListNotFoundException;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository.BookListRepository;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository.BookReservationRepository;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.ApartmentService;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ReservationsController {

    private final ReservationService reservationService;
    private final ApartmentService apartmentService;

    private final BookListRepository bookListRepository;

    private final BookReservationRepository bookReservationRepository;


    public ReservationsController(ReservationService reservationService,
                                  ApartmentService apartmentService,
                                  BookListRepository bookListRepository,
                                  BookReservationRepository bookReservationRepository) {
        this.reservationService = reservationService;
        this.apartmentService = apartmentService;
        this.bookListRepository = bookListRepository;
        this.bookReservationRepository = bookReservationRepository;
    }

    @GetMapping("/reservations")
    public String listReservations(Model model){

        model.addAttribute("reservations", this.reservationService.getAllReservations());

        return "reservations";
    }

    @GetMapping("/add/reservation")
    public String addReservationPage(Model model){

        model.addAttribute("apartments",this.apartmentService.getAllApartments());


        return "create_reservation";
    }
    @PostMapping ("/add/reservation")
    public String saveReservation(@RequestParam LocalDateTime localDateTime, @RequestParam Long id){

        this.reservationService.create(localDateTime, id);

        return "redirect:/reservations";
    }

    @GetMapping("/add/reservation/booklist/{id}")
    public String getReservationToBookListPage(Model model , @PathVariable Long id){


        Reservation reservation = this.reservationService.findById(id);

        AddReservationToBookListdto addReservationToBookListdto = new AddReservationToBookListdto(
                reservation.getId(),
                reservation,
                1

        );
        model.addAttribute("dtoReservation", addReservationToBookListdto);

        return "addToBookList";

    }

    @PostMapping("/add/reservation/booklist/{id}")

    public String update(@PathVariable Long id,@RequestParam(required = false) String apartmentName, @RequestParam double pricePerNight){

        BookList bookList = this.bookListRepository.findById(102L).orElseThrow(() -> new BookListNotFoundException(102L));

        Reservation reservation = this.reservationService.findById(id);

        BookReservationId bookReservationId = new BookReservationId(bookList.getId(), reservation.getId());

        BookReservation bookReservation = new BookReservation(bookReservationId,bookList,reservation,pricePerNight);



        this.bookReservationRepository.save(bookReservation);



        return "redirect:/reservations";

    }
}
