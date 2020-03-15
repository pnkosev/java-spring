package app.service.services.impl;

import app.data.models.User;
import app.data.repositories.UserRepository;
import app.service.models.UserAuthenticatedServiceModel;
import app.service.services.api.AuthService;
import app.service.models.UserLoginServiceModel;
import app.service.models.UserRegisterServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterServiceModel serviceModel) {
        this.userRepository.saveAndFlush(this.modelMapper.map(serviceModel, User.class));
    }

    @Override
    public UserAuthenticatedServiceModel login(UserLoginServiceModel serviceModel) {
        return this.userRepository
                .findByUsernameAndPassword(serviceModel.getUsername(), serviceModel.getPassword())
                .map(u -> {
                    String heroName = u.getHero() == null
                            ? null
                            : u.getHero().getUsername();
                    return new UserAuthenticatedServiceModel(u.getUsername(), heroName);
                })
                .orElse(null);
    }
}
