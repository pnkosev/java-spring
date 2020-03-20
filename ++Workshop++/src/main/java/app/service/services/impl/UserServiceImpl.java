package app.service.services.impl;

import app.data.models.User;
import app.data.repositories.UserRepository;
import app.error.UserNotFoundException;
import app.service.models.user.UserDetailsServiceModel;
import app.service.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetailsServiceModel getUserByName(String username) {
        Optional<User> optionalUser = this.userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("No such user!");
        }

        User user = optionalUser.get();

        return this.modelMapper.map(user, UserDetailsServiceModel.class);
    }
}
