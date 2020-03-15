package app.service.services.impl;

import app.data.models.User;
import app.data.repositories.UserRepository;
import app.service.models.UserAuthenticatedServiceModel;
import app.service.services.api.AuthService;
import app.service.models.UserLoginServiceModel;
import app.service.models.UserRegisterServiceModel;
import app.service.services.api.AuthValidationService;
import app.service.services.api.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final AuthValidationService validator;

    private final HashingService hashingService;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthValidationService validator, HashingService hashingService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.hashingService = hashingService;
    }

    @Override
    public void register(UserRegisterServiceModel serviceModel) {

        if (validator.isValid(serviceModel)) {
            User user = this.modelMapper.map(serviceModel, User.class);
            user.setPassword(this.hashingService.hash(user.getPassword()));
            this.userRepository.saveAndFlush(user);
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    @Override
    public UserAuthenticatedServiceModel login(UserLoginServiceModel serviceModel) {
        String hashedPassword = this.hashingService.hash(serviceModel.getPassword());

        return this.userRepository
                .findByUsernameAndPassword(serviceModel.getUsername(), hashedPassword)
                .map(u -> {
                    String heroName = u.getHero() == null
                            ? null
                            : u.getHero().getUsername();
                    return new UserAuthenticatedServiceModel(u.getUsername(), heroName);
                })
                .orElse(null);
    }
}
