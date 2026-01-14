package lab2.integrated.systems.bookingapplicationspringboot.repository.integration;

import lab2.integrated.systems.bookingapplicationspringboot.model.Person;
import lab2.integrated.systems.bookingapplicationspringboot.model.enums.Role;
import lab2.integrated.systems.bookingapplicationspringboot.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class PersonRepositoryIntegrationTest {

    private final PersonRepository personRepository;

    @Autowired
    public PersonRepositoryIntegrationTest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
    }
    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }
    @Test
    void returnOptionalOfPersonWhenFoundByEmail() {
        //given
        Person person = new Person("koc","leon.leon@outlook.com","1234", Role.ROLE_User);
        personRepository.save(person);
        //when
        Optional<Person> savedPerson = personRepository.findByEmail(person.getEmail());
        //then
        Assertions.assertTrue(savedPerson.isPresent());
    }
    @Test
    void returnOptionalOfPersonWhenFoundByUsername() {
        //given - e.g we are given saved object in DB
        Person person = new Person("koc","leon.leon@outlook.com","1234", Role.ROLE_User);
        personRepository.save(person);
        //when
        Optional<Person> savedPerson = personRepository.findByUsername(person.getUsername());
        //then
        Assertions.assertEquals(person.getEmail(), savedPerson.get().getEmail());
    }
}