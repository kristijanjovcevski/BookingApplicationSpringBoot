package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ApartmentSteps {

    public ApartmentSteps() {
        System.out.println("✅ ApartmentSteps loaded!");
    }

    @BeforeAll
    static void beforeAll() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");

        Configuration.browser = "chrome";
        Configuration.browserCapabilities = options;
        Configuration.baseUrl = "http://localhost:8080";
    }

    @Given("the user is on the add apartment page")
    void openAddApartmentPage(){
        open("/add/apartment");
    }
    @When("the user submits valid apartment details")
    void submitApartmentDetails() {
        $("input[name='apartmentName']").setValue("Dizzo");
        $("input[name='city']").setValue("Dojran");
        $("input[name='description']").setValue("Amazing");
        $("input[name='pricePerNight']").setValue("200");
        $("input[name='rating']").setValue("3.6");
        $("button").click();
    }
    @Then("user is redirected to apartments page and the apartment is added to the apartment list")
    void testAddApartmentForm() {
        Selenide.webdriver().shouldHave(urlContaining("/apartments"));
        $$("p:nth-child(1+5n)").find(text("Dizzo"));
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
