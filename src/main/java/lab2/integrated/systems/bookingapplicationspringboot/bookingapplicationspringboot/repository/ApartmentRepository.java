package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository <Apartment, Long> {

}
