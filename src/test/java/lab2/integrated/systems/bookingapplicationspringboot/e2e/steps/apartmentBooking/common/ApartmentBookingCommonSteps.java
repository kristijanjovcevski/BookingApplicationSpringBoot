package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.apartmentBooking.common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ApartmentBookingCommonSteps {

    @Given("the user is on the add apartment page")
    public void openAddApartmentPage() {
        open("/add/apartment");
    }

    @And("the user submits the form")
    public void submitForm() {
        $("button[type='submit']").click();
    }
}
