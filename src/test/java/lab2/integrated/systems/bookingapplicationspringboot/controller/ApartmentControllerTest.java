package lab2.integrated.systems.bookingapplicationspringboot.controller;


import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.model.dto.ApartmentDto;
import lab2.integrated.systems.bookingapplicationspringboot.service.ApartmentService;
import lab2.integrated.systems.bookingapplicationspringboot.web.controller.ApartmentController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



/* for slice testing the web layer and mock the service layer - or connect it to wiremock a fake server
@WebMvcTest(ApartmentController.class)
* */

@WebMvcTest(ApartmentController.class)
class ApartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApartmentService apartmentService;

    @Test
    void getApartments() throws Exception {
        mockMvc.perform(get("/apartments"))
                .andExpect(status().isOk());
    }

    @Test
    void saveApartment_Success() throws Exception {
        Apartment apartment = new Apartment("Mizo", "Ohrid", "Very good", 400,
                4.9);
        ApartmentDto apartmentDto = ApartmentDto.of(apartment);
        Mockito.when(apartmentService.createApartment(apartmentDto)).thenReturn(apartment);
        Apartment fetchedApartment = apartmentService.createApartment(apartmentDto);
        mockMvc.perform(post("/add/apartment")
                        .flashAttr("apartment", ApartmentDto.of(fetchedApartment)))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("/apartments"));
    }
}