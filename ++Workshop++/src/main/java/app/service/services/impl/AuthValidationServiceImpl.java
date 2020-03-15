package app.service.services.impl;

import app.data.repositories.UserRepository;
import app.service.models.UserRegisterServiceModel;
import app.service.services.api.AuthValidationService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final UserRepository userRepository;

    public AuthValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(UserRegisterServiceModel model) {
        return arePasswordsMatching(model.getPassword(), model.getConfirmPassword())
                && isEmailValid(model.getEmail())
                && isEmailFree(model.getEmail())
                && isUsernameFree(model.getUsername());
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    private boolean isEmailFree(String email) {
        return !userRepository.existsByEmail(email);
    }

    private boolean isUsernameFree(String username) {
        return !this.userRepository.existsByUsername(username);
    }
}
