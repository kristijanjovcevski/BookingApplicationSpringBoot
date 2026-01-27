package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.apartmentBooking.validation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class ApartmentBookingValidationSteps {

    @When("the user submits the apartment with {string} invalid value {string}")
    public void submitInvalidApartmentDetails(String field, String value, DataTable dataTable) {
        Map<String, String> data = new HashMap<>(dataTable.asMap(String.class, String.class));
        data.put(field, value);
        $("input[name='apartmentName']").setValue(data.get("apartmentName"));
        $("input[name='city']").setValue(data.get("city"));
        $("input[name='description']").setValue(data.get("description"));
        $("input[name='pricePerNight']").setValue(data.get("pricePerNight"));
        $("input[name='rating']").setValue(data.get("rating"));
    }

    @Then("the validation error for {string} should be displayed")
    public void validationErrorsShouldBeDisplayed (String field) {
        $("." + field + "-error").shouldNotBe(empty);
    }
}
