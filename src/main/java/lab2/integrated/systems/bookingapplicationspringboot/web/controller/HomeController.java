package lab2.integrated.systems.bookingapplicationspringboot.web.controller;

import lab2.integrated.systems.bookingapplicationspringboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home(){

        return "register";
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    /*@PostMapping("/register")
    public String register(String email, String firstName, String lastName, String address, String password){

        this.userService.register(email,firstName,lastName,address, password);

        return "redirect:/";
    }*/
}
