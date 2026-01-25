package lab2.integrated.systems.bookingapplicationspringboot.web.controller;

import jakarta.validation.Valid;
import lab2.integrated.systems.bookingapplicationspringboot.model.dto.ApartmentDto;
import lab2.integrated.systems.bookingapplicationspringboot.service.ApartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/apartments")
    public String getApartments(Model model) {
        List<ApartmentDto> apartmentDtoList = this.apartmentService.getAllApartments()
                .stream()
                .map(ApartmentDto::of)
                .toList();
        model.addAttribute("apartments", apartmentDtoList);
        return "index";
    }

    @GetMapping("/add/apartment")

    public String addApartment(Model model) {
        model.addAttribute("apartment", new ApartmentDto());
        return "add";
    }

        @PostMapping("/add/apartment")
        public String saveApartment(@Valid @ModelAttribute("apartment") ApartmentDto apartmentDto, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "add";
            }
            this.apartmentService.createApartment(apartmentDto);

            return "redirect:/apartments";
        }
}
