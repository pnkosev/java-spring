package pn.app.service.services;

import pn.app.service.models.OfferServiceModel;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService {

    void create(OfferServiceModel offer);

    List<OfferServiceModel> getAll();

    OfferServiceModel findOneByApartmentTypeAndApartmentRent(String type, BigDecimal rent);

    void delete(OfferServiceModel offer);
}
