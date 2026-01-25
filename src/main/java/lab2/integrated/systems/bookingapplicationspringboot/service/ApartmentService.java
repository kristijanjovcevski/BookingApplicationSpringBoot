package lab2.integrated.systems.bookingapplicationspringboot.service;


import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.model.dto.ApartmentDto;

import java.util.List;

public interface ApartmentService {

    List<Apartment> getAllApartments();

    Apartment getApartmentById(Long id);

    Apartment createApartment(ApartmentDto apartment);

    Apartment editApartment(Long id,Apartment apartment);

    Apartment deleteApartment(Long id);
    void deleteAll();
}
