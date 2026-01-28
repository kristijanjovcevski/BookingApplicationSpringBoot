package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.apartmentBooking.happyPath;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ApartmentBookingHappyPathSteps {

    // ========== REGRESSION FLOW STEPS ==========

    @When("the user submits valid apartment details")
    public void submitApartmentDetails() {
        fillValidApartmentFields();
    }

    private void fillValidApartmentFields() {
        $("input[name='apartmentName']").setValue("Dizzo");
        $("input[name='city']").setValue("Dojran");
        $("input[name='description']").setValue("Amazing apartment");
        $("input[name='pricePerNight']").setValue("200");
        $("input[name='rating']").setValue("3.6");
    }

    //  ========== EDGE CASE FLOW STEPS ==========
    @When("the user submits apartment field {string} with maximum allowed length")
    public void submitApartmentFieldMaxLength(String field) {
        Map<String, String> map = getMapMaxFieldLength(field);
        $("input[name='apartmentName']").setValue(map.get("apartmentName"));
        $("input[name='city']").setValue(map.get("city"));
        $("input[name='description']").setValue(map.get("description"));
        $("input[name='pricePerNight']").setValue(map.get("pricePerNight"));
        $("input[name='rating']").setValue(map.get("rating"));

    }

    @When("the user submits apartment field {string} with minimum allowed length")
    public void submitApartmentFieldMinLength(String field) {
        Map<String, String> map = getMapMinFieldLength(field);
        $("input[name='apartmentName']").setValue(map.get("apartmentName"));
        $("input[name='city']").setValue(map.get("city"));
        $("input[name='description']").setValue(map.get("description"));
        $("input[name='pricePerNight']").setValue(map.get("pricePerNight"));
        $("input[name='rating']").setValue(map.get("rating"));
    }

    @Then("user is redirected to apartments page and the apartment is added to the apartment list")
    public void addApartmentForm() {
        Selenide.webdriver().shouldHave(urlContaining("/apartments"));
    }

    private static @NonNull Map<String, String> getMapMaxFieldLength(String field) {
        Map<String, String> maxLengths = Map.of(
                "apartmentName", "oikq1fghijklooopqrstuvwxyzoqpz",
                "city", "oikq1fghijklooopqrstuvwxyzoqpz",
                "description", "u".repeat(98),
                "pricePerNight", "3000",
                "rating", "5.0"
        );

        Map<String, String> map = new HashMap<>(Map.of(
                "apartmentName", "Good hotel",
                "city", "New York",
                "description", "Nice view",
                "pricePerNight", "200",
                "rating", "4.5"
        ));
        map.put(field, maxLengths.get(field));
        return map;
    }

    private static @NonNull Map<String, String> getMapMinFieldLength(String field) {
        Map<String, String> minLength = Map.of(
                "apartmentName", "abcd",
                "city", "ob",
                "description", "popokata",
                "pricePerNight", "100",
                "rating", "0"
        );
        Map<String, String> map = new HashMap<>(Map.of(
                "apartmentName", "Good hotel",
                "city", "New York",
                "description", "Nice view",
                "pricePerNight", "200",
                "rating", "4.5"
        ));
        map.put(field, minLength.get(field));
        return map;
    }



    //to leave
    /*@Test
    void testCreateReservationForm() {
        open("/add/reservation");
        $("select").shouldBe(visible);
        $$("option").shouldHave(sizeGreaterThan(0));
        $("input[name='localDateTime']").setValue(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm a")));
        $("button").click();
        Selenide.webdriver().shouldHave(urlContaining("/reservations"));
    }*/
}
