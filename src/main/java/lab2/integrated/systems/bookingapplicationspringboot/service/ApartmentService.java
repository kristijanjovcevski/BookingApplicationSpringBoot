package lab2.integrated.systems.bookingapplicationspringboot.service;


import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;

import java.util.List;

public interface ApartmentService {

    List<Apartment> getAllApartments();

    Apartment getApartmentById(Long id);

    Apartment createApartment( String apartmentName, String city, String description,
                               int pricePerNight, double rating);

    Apartment editApartment(Long id,Apartment apartment);

    Apartment deleteApartment(Long id);
    void deleteAll();
}
