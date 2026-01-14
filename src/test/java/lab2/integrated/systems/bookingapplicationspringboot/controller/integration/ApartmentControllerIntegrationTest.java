package lab2.integrated.systems.bookingapplicationspringboot.controller.integration;


import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.service.ApartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



/* for slice testing only the web layer and mock the service layer -or connect it to wiremock a fake server
@WebMvcTest(ApartmentController.class)
* */

// core backend testing but not end to end (since external call API is not tested but mock)

/*
fake web server
@EnableWireMock(
        @ConfigureWireMock(
                baseUrlProperties ="localhost",
                port = 8080
        )
)*/


/*
SpringBootTest.WebEnvironment.DEFINED_PORT,RANDOM_PORT etc, sets
TestRestTemplate automatically configured to send requests to
http://localhost at the running server's port.
*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ApartmentControllerIntegrationTest {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    void tearDown(){
        apartmentService.deleteAll();
    }

    @Test
    void saveApartment_Success() {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("apartmentName","Mizo");
        params.add("city","Ohrid");
        params.add("description","Very+good");
        params.add("pricePerNight","400");
        params.add("rating","4.9");

        restTemplate.postForEntity("/add/apartment",params,String.class);
    }

    @Test
    void deleteApartment_Success() {
        Apartment apartment = new Apartment("Mizo","Ohrid","Very Good",400,4.9);
        Apartment savedApartment = apartmentService.createApartment(apartment.getApartmentName(), apartment.getCity(), apartment.getDescription(),
                apartment.getPricePerNight(), apartment.getRating());
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("id",String.valueOf(savedApartment.getId()));
        restTemplate.postForEntity("/api/apartments/delete/" + savedApartment.getId(), params, String.class);
        Assertions.assertTrue(apartmentService.getAllApartments().isEmpty());
    }
}