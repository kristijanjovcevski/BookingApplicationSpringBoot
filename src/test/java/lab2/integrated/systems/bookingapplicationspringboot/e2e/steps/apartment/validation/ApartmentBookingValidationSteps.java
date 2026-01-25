package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.apartment.validation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ApartmentBookingValidationSteps {

    @When("the user attempts to create apartment with completely invalid data")
    public void submitInvalidApartmentDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        $("input[name='apartmentName']").setValue(data.get("apartmentName"));
        $("input[name='city']").setValue(data.get("city"));
        $("input[name='description']").setValue(data.get("description"));
        $("input[name='pricePerNight']").setValue(data.get("pricePerNight"));
        $("input[name='rating']").setValue(data.get("rating"));
    }

    @And("the user submits the form")
    public void submitForm() {
        $("button[type='submit']").click();
    }

    @Then("validation errors should be displayed")
    public void validationErrorsShouldBeDisplayed () {
        $(".apartmentName-error")
                .shouldHave(text("Apartment name must be a text!"));

        $(".city-error")
                .shouldHave(text("City name is required!"));

        $(".description-error")
                .shouldHave(text("Description must be a text!"));

        $(".pricePerNight-error")
                .shouldHave(text("Price must be an integer!"));

        $(".rating-error")
                .shouldHave(text("Rating must be text!"));
    }
}
