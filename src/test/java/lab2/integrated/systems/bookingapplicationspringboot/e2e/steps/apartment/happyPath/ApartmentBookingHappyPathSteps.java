package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.apartment.happyPath;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.apartment.common.ApartmentBookingCommonSteps;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Condition.text;
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
        $("button").click();
    }

    @Then("user is redirected to apartments page and the apartment is added to the apartment list")
    public void addApartmentForm() {
        Selenide.webdriver().shouldHave(urlContaining("/apartments"));
        $$("p:nth-child(1+5n)").find(text("Dizzo"));
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
