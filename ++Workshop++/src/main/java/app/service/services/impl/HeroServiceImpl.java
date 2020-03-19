package app.service.services.impl;

import app.data.models.Gender;
import app.data.models.Hero;
import app.data.models.User;
import app.data.repositories.HeroRepository;
import app.data.repositories.UserRepository;
import app.error.HeroNotFoundException;
import app.service.factories.HeroFactory;
import app.service.factories.HeroFactoryImpl;
import app.service.models.hero.HeroDetailsServiceModel;
import app.service.models.hero.HeroServiceModel;
import app.service.models.item.ItemServiceModel;
import app.service.services.api.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public HeroServiceImpl(HeroRepository heroRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(HeroServiceModel model) {
        HeroFactory heroFactory = new HeroFactoryImpl();
        Hero hero = heroFactory.create(model.getName(), Gender.valueOf(model.getGender().toUpperCase()));
        User user = this.userRepository.findByUsername(model.getUserUsername());
        user.setHero(hero);
        hero.setUser(user);

        this.heroRepository.saveAndFlush(hero);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public HeroDetailsServiceModel getByHeroName(String heroName) {
        Optional<Hero> optionalHero = this.heroRepository.getByNameIgnoreCase(heroName);

        if (optionalHero.isEmpty()) {
            throw new HeroNotFoundException("No such hero found!");
        }

        Hero hero = optionalHero.get();
        HeroDetailsServiceModel heroDetailsModel = this.modelMapper.map(hero, HeroDetailsServiceModel.class);

        List<ItemServiceModel> items = hero.getItems()
                .stream()
                .map(i -> this.modelMapper.map(i, ItemServiceModel.class))
                .collect(Collectors.toList());

        heroDetailsModel.setItems(items);

        return heroDetailsModel;
    }
}
