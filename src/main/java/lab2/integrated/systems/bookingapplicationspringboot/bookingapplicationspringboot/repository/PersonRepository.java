package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    Optional<Person> findByEmail(String email);

    Optional<Person> findByUsername(String username);

    Optional<Person> findByEmailAndPassword(String email, String password);

}
