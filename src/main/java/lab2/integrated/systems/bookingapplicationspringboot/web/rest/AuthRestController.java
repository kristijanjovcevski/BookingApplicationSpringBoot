package lab2.integrated.systems.bookingapplicationspringboot.web.rest;

import lab2.integrated.systems.bookingapplicationspringboot.model.dto.RegisterDto;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.business.TermsOfServiceException;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.conflict.EmailAlreadyExistsException;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.conflict.InvalidArgumentsException;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.conflict.UsernameAlreadyExistsException;
import lab2.integrated.systems.bookingapplicationspringboot.model.exceptions.unauthorized.PasswordsDoNotMatchException;
import lab2.integrated.systems.bookingapplicationspringboot.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = {"https://bookapartment.vercel.app"})
public class AuthRestController {

    private final PersonService personService;

    public AuthRestController(PersonService personService) {
        this.personService = personService;
    }

   /* @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login
                                   ){
        try {

            this.personService.login(login.getEmail(), login.getPassword());

            return ResponseEntity.ok().build();


        } catch (InvalidArgumentsException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

        }
    }*/


    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto register){

        try {
            if (!register.getAgree()){
                throw new TermsOfServiceException();
            }
            this.personService.register(register.getUsername(),register.getEmail(), register.getPassword(), register.getConfirmPassword());

            return ResponseEntity.ok().build();
        }catch (TermsOfServiceException | InvalidArgumentsException | PasswordsDoNotMatchException |
                UsernameAlreadyExistsException | EmailAlreadyExistsException exception){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }


    /*@PostMapping("/register")
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
    }*/
}
