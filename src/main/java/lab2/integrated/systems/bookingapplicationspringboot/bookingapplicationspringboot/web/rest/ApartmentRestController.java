package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.web.rest;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.dto.Apartmentdto;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.ApartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://book-reservation-for-apartment-42mn.vercel.app"})
@RequestMapping("/api/apartments")
public class ApartmentRestController {

    private final ApartmentService apartmentService;



    public ApartmentRestController(ApartmentService apartmentService ) {
        this.apartmentService = apartmentService;


    }

    @GetMapping
    public List<Apartment> findAll(){

        return this.apartmentService.getAllApartments();
    }


    @GetMapping("/find/{id}")
    public Apartment findApartment(@PathVariable Long id){
        return this.apartmentService.getApartmentById(id);
    }

    @PostMapping("/create")
    public String create(@RequestBody Apartmentdto apartmentdto){

         this.apartmentService.createApartment(apartmentdto.getApartmentName(),
                apartmentdto.getCity(), apartmentdto.getDescription(), apartmentdto.getPricePerNight(),
                apartmentdto.getRating());
         return "redirect:/api/apartments";
    }


    @PostMapping ("/update/{id}")
    public String update(@RequestBody Apartment apartment){


        this.apartmentService.editApartment(apartment.getId(), apartment);

        return "redirect:/api/apartments";

    }

    @PostMapping("/delete/{id}")

    public String delete(@PathVariable Long id){

        this.apartmentService.deleteApartment(id);
        return "redirect:/api/apartments";

    }
}
