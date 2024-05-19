package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.bootstrapdata;

import jakarta.annotation.PostConstruct;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Administrator;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Person;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.enums.Role;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository.AdministratorRepository;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final PersonRepository personRepository;
    private final AdministratorRepository administratorRepository;

    private final PasswordEncoder passwordEncoder;

    public DataLoader(PersonRepository personRepository, AdministratorRepository administratorRepository,
                      PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initAdministrators(){

        if (this.personRepository.findByEmail("admin@outlook.com").isEmpty()){
            Person person = this.personRepository.save(new Person(passwordEncoder.encode("admin"),
                    "admin@outlook.com","admin", Role.ROLE_Admin));

            this.administratorRepository.save(new Administrator(person));
        }
    }

}
