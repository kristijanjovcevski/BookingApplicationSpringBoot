package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model;

import jakarta.persistence.*;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.enums.Role;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String email;

    private String username;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Person(String password, String email, String username, Role role) {
        this.password = password;
        this.email = email;
        this.username = username;
        this.role = role;
    }
}
