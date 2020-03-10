package pn.app.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pn.app.data.models.Offer;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
    List<Offer> findByApartmentTypeAndApartmentRentLessThan(String apartmentType, BigDecimal apartmentRent);
}
