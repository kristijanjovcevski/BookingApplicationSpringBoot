package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.apartment.common;

import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Selenide.open;

public class ApartmentBookingCommonSteps {

    public ApartmentBookingCommonSteps() {
        System.out.println("✅ ApartmentSteps loaded!");
    }

    @Given("the user is on the add apartment page")
    public void openAddApartmentPage() {
        open("/add/apartment");
    }
}
