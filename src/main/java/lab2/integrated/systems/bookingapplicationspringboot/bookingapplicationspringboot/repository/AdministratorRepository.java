package lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.repository;

import lab2.integrated.systems.bookingapplicationspringboot.bookingapplicationspringboot.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Long> {

}
