package app.service.services.api;

import app.service.models.hero.HeroDetailsServiceModel;
import app.service.models.hero.HeroServiceModel;
import app.service.models.item.ItemServiceModel;

import java.util.Optional;

public interface HeroService {

    void create(HeroServiceModel model);

    HeroDetailsServiceModel getByHeroName(String heroName);

    void updateHeroItem(String heroName, String itemName);
}
