package lab2.integrated.systems.bookingapplicationspringboot.web.controller;

import lab2.integrated.systems.bookingapplicationspringboot.service.ApartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/apartments")

    public String getApartments(Model model){

        model.addAttribute("apartments",this.apartmentService.getAllApartments());
        return "index";
    }

    @GetMapping ("/add/apartment")

    public String addApartment(){

        return "add";
    }
    @PostMapping("/add/apartment")

    public String saveApartment(String apartmentName, String city, String description,
                                int pricePerNight, double rating){

        this.apartmentService.createApartment(apartmentName, city, description,
         pricePerNight,  rating);

        return "redirect:/apartments";
    }
}
