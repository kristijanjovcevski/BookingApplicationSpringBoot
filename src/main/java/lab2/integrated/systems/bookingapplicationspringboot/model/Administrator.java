package lab2.integrated.systems.bookingapplicationspringboot.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BookingApplicationAdmin")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Person person;

    public Administrator(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", person=" + person.getUsername() +
                '}';
    }
}

