package lab2.integrated.systems.bookingapplicationspringboot.api;

import io.restassured.http.ContentType;
import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApartmentApiTest {
    private final String baseURI = "http://localhost:8080";

    @AfterEach
    void tearDown() {
        given()
                .baseUri(baseURI)
                .when()
                .post("/api/apartments/delete/all")
                .then()
                .statusCode(200);
    }

    @Test
    void testCreateAndFindApartmentById() {
         Long apartmentId = given()
                .baseUri(baseURI)
                .contentType("application/json")
                .when()
                .body(new Apartment("Mizo","Ohrid","Very good",400,4.9))
                .post("/api/apartments/create")
                .then()
                .statusCode(200).extract().as(Apartment.class).getId();

        given()
                .baseUri(baseURI)
                .when()
                .pathParam("id",apartmentId)
                .get("/api/apartments/find/{id}")
                .then()
                .statusCode(200)
                .extract().as(Apartment.class);
    }
    @Test
    void testCreateAndDeleteApartmentById() {
        Long apartmentId = given()
                .baseUri(baseURI)
                .contentType("application/json")
                .when()
                .body(new Apartment("Mizo","Ohrid","Very good",400,4.9))
                .post("/api/apartments/create")
                .then()
                .statusCode(200).extract().as(Apartment.class).getId();
        given()
                .baseUri(baseURI)
                .when()
                .pathParam("id", apartmentId)
                .post("/api/apartments/delete/{id}")
                .then()
                .statusCode(200)
                .extract().as(Apartment.class);

        boolean isTableEmpty = given()
                .baseUri(baseURI)
                .when()
                .get("/api/apartments")
                .then()
                .statusCode(200)
                .extract().as(List.class).isEmpty();
        Assertions.assertTrue(isTableEmpty);
    }

    @Test
    void testCreateAndUpdateApartment() {
        Apartment apartment = given()
                .baseUri(baseURI)
                .contentType("application/json")
                .when()
                .body(new Apartment("Mizo", "Ohrid", "Very good", 400, 4.9))
                .post("/api/apartments/create")
                .then()
                .statusCode(200).extract().as(Apartment.class);

        apartment.setApartmentName("New Mizo");
        apartment.setCity("Skopje");
        apartment.setDescription("Sehr gut");
        apartment.setPricePerNight(100);
        apartment.setRating(4.1);

        given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .when()
                .pathParam("id",apartment.getId())
                .body(apartment)
                .post("/api/apartments/update/{id}")
                .then()
                .statusCode(200)
                .body("apartmentName",equalTo("New Mizo"))
                .body("city",equalTo("Skopje"))
                .body("description",equalTo("Sehr gut"))
                .body("pricePerNight", equalTo(100))
                .body("rating", equalTo( 4.1f))
                .extract().as(Apartment.class);
    }
}
