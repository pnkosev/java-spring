package app.service.services.impl;

import app.data.models.*;
import app.data.repositories.HeroRepository;
import app.data.repositories.UserRepository;
import app.error.HeroNotFoundException;
import app.error.UserNotFoundException;
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
import java.util.stream.IntStream;

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

        Optional<User> userOptional = this.userRepository.findByUsername(model.getUserUsername());

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("No such user exists!");
        }

        User user = userOptional.get();

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

        heroDetailsModel.setHelmet(getItemBySlot(hero.getItems(), Slot.HELMET));
        heroDetailsModel.setHelmet(getItemBySlot(hero.getItems(), Slot.WEAPON));
        heroDetailsModel.setHelmet(getItemBySlot(hero.getItems(), Slot.PAULDRON));
        heroDetailsModel.setHelmet(getItemBySlot(hero.getItems(), Slot.GAUNTLETS));
        heroDetailsModel.setHelmet(getItemBySlot(hero.getItems(), Slot.PADS));

        return heroDetailsModel;
    }

    private ItemServiceModel getItemBySlot(List<Item> items, Slot slot) {
        Optional<Item> item = items
                .stream()
                .filter(i -> i.getSlot().equals(slot))
                .findFirst();

        return item.isPresent()
                ? this.modelMapper.map(item, ItemServiceModel.class)
                : null;
    }
}
