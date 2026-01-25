package lab2.integrated.systems.bookingapplicationspringboot.service.unit;

import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.model.dto.ApartmentDto;
import lab2.integrated.systems.bookingapplicationspringboot.repository.ApartmentRepository;
import lab2.integrated.systems.bookingapplicationspringboot.service.impl.ApartmentServiceImpl;
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
        Mockito.when(apartmentRepository.findAll()).thenReturn(apartmentList);
        assertEquals(apartmentService.getAllApartments(), apartmentList);
        Mockito.verify(apartmentRepository).findAll();
    }

    @Test
    void testGetApartmentById() {
        Apartment apartment = new Apartment("Mizo","Ohrid","Super",100,4.9);
        Mockito.when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        assertEquals(Optional.of(apartmentService.getApartmentById(1L)), Optional.of(apartment));
        Mockito.verify(apartmentRepository).findById(1L);
    }

    @Test
    void testCreateApartment() {
        Apartment apartment = new Apartment("Mizo","Ohrid","Super",100,4.9);
        Mockito.when(apartmentRepository.save(apartment)).thenReturn(apartment);
        assertEquals(this.apartmentService.createApartment(ApartmentDto.of(apartment)).getApartmentName(), apartment.getApartmentName());
        Mockito.verify(apartmentRepository).save(apartment);
    }
    @Test
    void testDelete_Success(){
        Apartment apartment = new Apartment();
        apartment.setId(1L);
        Mockito.when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        Mockito.doNothing().when(apartmentRepository).delete(Mockito.any(Apartment.class));
        apartmentService.deleteApartment(apartment.getId());
        Mockito.verify(apartmentRepository).findById(1L);
        Mockito.verify(apartmentRepository).delete(Mockito.any(Apartment.class));
    }
}
