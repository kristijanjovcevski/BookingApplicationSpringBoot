package lab2.integrated.systems.bookingapplicationspringboot.repository.integration;

import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.repository.ApartmentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ActiveProfiles("test")
class ApartmentRepositoryIntegrationTest {

    @Autowired
    private ApartmentRepository apartmentRepository;
    @BeforeEach
    void setUp() {
        apartmentRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        apartmentRepository.deleteAll();
    }
    @Test
    @Order(1)
    void saveApartment() {
        Apartment apartment = new Apartment("Mizo","Ohrid","Super",100,4.9);
        apartmentRepository.save(apartment);
        Assertions.assertEquals(1, apartmentRepository.findAll().size());
    }

    @Test
    void findById() {
        Apartment apartment = new Apartment("Mizo","Ohrid","Super",100,4.9);
        Apartment savedApartment = apartmentRepository.save(apartment);
        Assertions.assertTrue(apartmentRepository.findById(savedApartment.getId()).isPresent());
        Assertions.assertEquals(apartment.getApartmentName(), apartmentRepository.findById(savedApartment.getId())
                .get().getApartmentName());
    }
    @Test
    void delete() {
        Apartment apartment = new Apartment("Mizo","Ohrid","Super",100,4.9);
        apartmentRepository.save(apartment);
        apartmentRepository.delete(apartment);
        Assertions.assertFalse(apartmentRepository.existsById(apartment.getId()));
    }
}