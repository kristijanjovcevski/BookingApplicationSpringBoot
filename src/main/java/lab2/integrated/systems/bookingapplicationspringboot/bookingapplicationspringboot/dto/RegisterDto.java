package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private Boolean agree;

}
