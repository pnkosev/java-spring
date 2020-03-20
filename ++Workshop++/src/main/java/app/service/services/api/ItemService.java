package app.service.services.api;

import app.service.models.item.ItemCreateServiceModel;
import app.service.models.item.ItemServiceModel;

import java.util.List;

public interface ItemService {

    void create(ItemCreateServiceModel itemServiceModel);

    List<ItemServiceModel> getAllItems(String heroName);
}
