package lab2.integrated.systems.bookingapplicationspringboot.model;

import jakarta.persistence.*;
import lab2.integrated.systems.bookingapplicationspringboot.model.enums.Role;
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

    public Person(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
