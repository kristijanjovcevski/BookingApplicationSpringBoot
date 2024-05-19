package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.dto;

import lombok.Data;

@Data
public class Apartmentdto {

    private String apartmentName;

    private String city;

    private String description;

    private int pricePerNight;

    private double rating;

    public Apartmentdto(String apartmentName, String city, String description, int pricePerNight, double rating) {
        this.apartmentName = apartmentName;
        this.city = city;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.rating = rating;
    }
}
