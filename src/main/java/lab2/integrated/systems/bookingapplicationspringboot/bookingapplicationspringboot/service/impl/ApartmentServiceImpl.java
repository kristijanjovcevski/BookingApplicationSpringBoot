package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.impl;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Apartment;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.exceptions.ApartmentNotFoundException;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository.ApartmentRepository;
import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.service.ApartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;

    }


    @Override
    public List<Apartment> getAllApartments() {
        return this.apartmentRepository.findAll();
    }

    public Apartment getApartmentById(Long id){


        return this.apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(id));
    }

    @Override
    public Apartment createApartment(String apartmentName, String city,
                                     String description, int pricePerNight, double rating) {

        return this.apartmentRepository.save(new Apartment(apartmentName,city,description,
                pricePerNight, rating));
    }

    @Override
    public Apartment editApartment(Long id,Apartment apartmentParam) {
        Apartment apartment = this.getApartmentById(id);

        apartment.setApartmentName(apartmentParam.getApartmentName());
        apartment.setCity(apartmentParam.getCity());
        apartment.setDescription(apartmentParam.getDescription());
        apartment.setPricePerNight(apartmentParam.getPricePerNight());
        apartment.setRating(apartmentParam.getRating());

        return this.apartmentRepository.save(apartment);


    }

    @Override
    public Apartment deleteApartment(Long id) {

        Apartment apartment = this.getApartmentById(id);

        this.apartmentRepository.delete(apartment);

        //this.reservationRepository.deleteByApartmentId(id);

        return apartment;
    }


}
