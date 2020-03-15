package app.service.services.impl;

import app.data.models.Gender;
import app.data.models.Hero;
import app.data.models.User;
import app.data.repositories.HeroRepository;
import app.data.repositories.UserRepository;
import app.service.models.HeroServiceModel;
import app.service.services.api.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        Hero hero = this.modelMapper.map(model, Hero.class);
        hero.setGender(Gender.valueOf(model.getGender().toUpperCase()));
        User user = this.userRepository.findByUsername(model.getUserUsername());
        user.setHero(hero);
        hero.setUser(user);

        this.heroRepository.saveAndFlush(hero);
        this.userRepository.saveAndFlush(user);
    }
}
