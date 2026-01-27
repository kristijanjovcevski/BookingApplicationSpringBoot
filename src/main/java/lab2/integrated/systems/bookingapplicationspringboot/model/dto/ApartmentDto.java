package lab2.integrated.systems.bookingapplicationspringboot.model.dto;

import jakarta.validation.constraints.*;
import lab2.integrated.systems.bookingapplicationspringboot.model.Apartment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApartmentDto {

    @NotBlank(message = "Apartment name must be a text!")
    @Pattern(regexp = "^[a-zA-Z].*", message = "Apartment name must start with a letter!")
    @Size(min = 2, max = 30)
    private String apartmentName;

    @NotBlank(message = "City name is required!")
    @Pattern(regexp = "^[a-zA-Z].*", message = "City name must start with a letter!")
    @Size(min = 2, max = 30)
    private String city;

    @NotBlank(message = "Description is required!")
    @Pattern(regexp = "^[a-zA-Z].*", message = "Description must be a text!")
    @Size(min = 3, max = 100)
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 10)
    @Digits(integer = 4, fraction = 0,  message = "Phone number must have at most 10 digits and no decimal point")
    private BigDecimal pricePerNight;

    @NotNull(message = "Rating is required!")
    @DecimalMin(value = "0.0", message = "Rating must be at least 0!")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5!")
    private Double rating;

    public static ApartmentDto of(Apartment apartment) {
        return new ApartmentDto(
                apartment.getApartmentName(),
                apartment.getCity(),
                apartment.getDescription(),
                BigDecimal.valueOf(apartment.getPricePerNight()),
                apartment.getRating()
        );
    }
}
