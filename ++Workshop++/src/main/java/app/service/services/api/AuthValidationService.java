package app.service.services.api;

import app.service.models.user.UserRegisterServiceModel;

public interface AuthValidationService {
    boolean isValid(UserRegisterServiceModel model);
}
