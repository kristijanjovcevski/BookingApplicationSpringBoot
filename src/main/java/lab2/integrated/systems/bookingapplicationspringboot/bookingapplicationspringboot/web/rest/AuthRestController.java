package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.web.rest;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.exceptions.*;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://book-reservation-for-apartment-42mn.vercel.app"})
public class AuthRestController {

    private final PersonService personService;

    public AuthRestController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password
    ){
        try {

            this.personService.login(email,password);

            return ResponseEntity.ok().build();


        }catch (InvalidArgumentsException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestParam String username, @RequestParam String email, @RequestParam String password,
                                       @RequestParam String confirmPassword, @RequestParam(defaultValue = "false") Boolean agree){

        try {
            if (!agree){
                throw new TermsOfServiceException();
            }
            this.personService.register(username,email,password,confirmPassword);

            return ResponseEntity.ok().build();
        }catch (TermsOfServiceException | InvalidArgumentsException | PasswordsDoNotMatchException |
                UsernameAlreadyExistsException | EmailAlreadyExistsException exception){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
