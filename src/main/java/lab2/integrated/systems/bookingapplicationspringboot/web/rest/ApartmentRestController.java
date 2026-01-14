package lab2.integrated.systems.bookingapplicationspringboot.web.rest;

import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.service.ApartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://bookapartment.vercel.app"})
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
    public Apartment create(@RequestBody Apartment apartment){

         return this.apartmentService.createApartment(apartment.getApartmentName(),
                apartment.getCity(), apartment.getDescription(), apartment.getPricePerNight(),
                apartment.getRating());
    }


    @PostMapping ("/update/{id}")
    public Apartment update(@PathVariable Long id, @RequestBody Apartment apartment){
        //security check if the ids are are real - at least one of them
        if (!id.equals(apartment.getId()))
            throw new IllegalArgumentException("Id in URL must match id in request body");
        return this.apartmentService.editApartment(id, apartment);
    }
    @PostMapping("/delete/{id}")
    public Apartment delete(@PathVariable Long id){
        return this.apartmentService.deleteApartment(id);
    }

    @PostMapping("/delete/all")
    public void deleteAll() {
        this.apartmentService.deleteAll();
    }
}
