package lab2.integrated.systems.bookingapplicationspringboot.model;



//spring data jpa treba za ova

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Apartment {

    public Apartment() {

    }

    @Id
    @GeneratedValue
    private Long id;

    private String apartmentName;

    private String city;

    private String description;

    private int pricePerNight;

    private double rating;

  /*  @OneToOne(mappedBy = "apartment" , cascade = CascadeType.ALL)
    private Reservation reservation;*/

    public Apartment(String apartmentName, String city, String description, int pricePerNight, double rating) {
        this.apartmentName = apartmentName;
        this.city = city;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.rating = rating;

    }

    @Override
    public String toString() {
        return
                ", apartmentName='" + apartmentName + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", rating=" + rating +
                '}';
    }
}
