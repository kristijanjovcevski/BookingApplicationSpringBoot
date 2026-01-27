package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.apartmentBooking.happyPath;

import com.codeborne.selenide.Selenide;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ApartmentBookingHappyPathSteps {



    // ========== REGRESSION FLOW STEPS ==========

    @When("the user submits valid apartment details")
    public void submitApartmentDetails() {
        $("input[name='apartmentName']").setValue("Dizzo");
        $("input[name='city']").setValue("Dojran");
        $("input[name='description']").setValue("Amazing");
        $("input[name='pricePerNight']").setValue("200");
        $("input[name='rating']").setValue("3.6");
    }

    //  ========== Edge case FLOW STEPS ==========
    @When("the user submits apartment field {string} with maximum allowed text length")
    public void submitApartmentNameMaxLength(String field, DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        $("input[name='apartmentName']").setValue(data.get("apartmentName"));
        $("input[name='city']").setValue(data.get("city"));
        $("input[name='description']").setValue(data.get("description"));
        $("input[name='pricePerNight']").setValue(data.get("pricePerNight"));
        $("input[name='rating']").setValue(data.get("rating"));
    }

    @Then("user is redirected to apartments page and the apartment is added to the apartment list")
    public void addApartmentForm() {
        Selenide.webdriver().shouldHave(urlContaining("/apartments"));
    }



    // ========== Negative FLOW STEPS ==========


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
