package pn.app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.app.data.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
}
