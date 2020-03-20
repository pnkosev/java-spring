package app.service.services.impl;

import app.data.models.*;
import app.data.repositories.HeroRepository;
import app.data.repositories.ItemRepository;
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

@Service
public class HeroServiceImpl implements HeroService {

    private final ItemRepository itemRepository;

    private final HeroRepository heroRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public HeroServiceImpl(ItemRepository itemRepository, HeroRepository heroRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
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
        Hero hero = this.getHero(heroName);
        HeroDetailsServiceModel heroDetailsModel = this.modelMapper.map(hero, HeroDetailsServiceModel.class);

        heroDetailsModel.setHelmet(getItemBySlot(hero.getItems(), Slot.HELMET));
        heroDetailsModel.setWeapon(getItemBySlot(hero.getItems(), Slot.WEAPON));
        heroDetailsModel.setPauldron(getItemBySlot(hero.getItems(), Slot.PAULDRON));
        heroDetailsModel.setGauntlets(getItemBySlot(hero.getItems(), Slot.GAUNTLETS));
        heroDetailsModel.setPads(getItemBySlot(hero.getItems(), Slot.PADS));

        return heroDetailsModel;
    }

    @Override
    public void updateHeroItem(String heroName, String itemName) {
        Hero hero = this.getHero(heroName);

        Item item = this.itemRepository.findByName(itemName);

        if (hero.getItems().indexOf(item) < 0) {
            int indexOfItem = getIndexOfSlot(hero.getItems(), item.getSlot());

            if (indexOfItem > -1) {
                Item oldItem = hero.getItems().get(indexOfItem);
                Item oldItemEntity = this.itemRepository.findByName(oldItem.getName());
                oldItemEntity.getHeroes().remove(hero);
                this.itemRepository.saveAndFlush(oldItemEntity);
                this.removeStatsFromHero(hero, oldItem);
                hero.getItems().set(indexOfItem, item);
            } else {
                hero.getItems().add(item);
            }

            this.addStatsToHero(hero, item);
            item.getHeroes().add(hero);

            this.itemRepository.saveAndFlush(item);
            this.heroRepository.saveAndFlush(hero);
        }

    }

    private void removeStatsFromHero(Hero hero, Item item) {
        hero.setStamina(hero.getStamina() - item.getStamina());
        hero.setStrength(hero.getStrength() - item.getStrength());
        hero.setAttack(hero.getAttack() - item.getAttack());
        hero.setDefense(hero.getDefense() - item.getDefense());
    }

    private void addStatsToHero(Hero hero, Item item) {
        hero.setStamina(hero.getStamina() + item.getStamina());
        hero.setStrength(hero.getStrength() + item.getStrength());
        hero.setAttack(hero.getAttack() + item.getAttack());
        hero.setDefense(hero.getDefense() + item.getDefense());
    }

    private Hero getHero(String heroName) {
        Optional<Hero> optionalHero = this.heroRepository.getByNameIgnoreCase(heroName);

        if (optionalHero.isEmpty()) {
            throw new HeroNotFoundException("No such hero found!");
        }

        return optionalHero.get();
    }

    private int getIndexOfSlot(List<Item> items, Slot slot) {
        Optional<Item> item = items
                .stream()
                .filter(i -> i.getSlot().equals(slot))
                .findFirst();

        return item.map(items::indexOf).orElse(-1);
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
