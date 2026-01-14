package lab2.integrated.systems.bookingapplicationspringboot.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    /*String username, String email, String password, String repeatPassword*/

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private Boolean agree;

}
