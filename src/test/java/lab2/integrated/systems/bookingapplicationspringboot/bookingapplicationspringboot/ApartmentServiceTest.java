package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository.ApartmentRepository;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.impl.ApartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ApartmentServiceTest {

    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentServiceImpl apartmentService;

    @Test
    void testGetAllApartments() {
        List<Apartment> apartmentList = List.of(new Apartment("Mizo","Ohrid","Super",100,4.9),
                new Apartment("Tizo","Prespa","Top", 150, 4.8));
        //when findAll is called return apartmentList
        Mockito.when(apartmentRepository.findAll()).thenReturn(apartmentList);
        assertEquals(apartmentService.getAllApartments(), apartmentList);
    }

    @Test
    void testGetApartmentById() {
        Apartment apartment = new Apartment("Mizo","Ohrid","Super",100,4.9);
        Mockito.when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        assertEquals(Optional.of(apartmentService.getApartmentById(1L)), Optional.of(apartment));
    }

    @Test
    void testCreateApartment() {
        Apartment apartment = new Apartment("Mizo","Ohrid","Super",100,4.9);
        Mockito.when(apartmentRepository.save(apartment)).thenReturn(apartment);
        assertEquals(this.apartmentService.createApartment(apartment.getApartmentName(), apartment.getCity(), apartment.getDescription(), apartment.getPricePerNight(),
                apartment.getRating()), apartment);
    }
}
