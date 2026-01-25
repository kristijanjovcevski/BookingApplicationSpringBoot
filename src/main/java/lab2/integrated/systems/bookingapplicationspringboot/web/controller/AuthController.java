package lab2.integrated.systems.bookingapplicationspringboot.web.controller;

import lab2.integrated.systems.bookingapplicationspringboot.model.dto.LoginDto;
import lab2.integrated.systems.bookingapplicationspringboot.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class AuthController {

    private final PersonService personService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto) {
        this.personService.login(loginDto.getEmail(), loginDto.getPassword());
        return "redirect:/apartments";
    }

}
