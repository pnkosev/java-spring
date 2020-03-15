package app.service.services.api;

import app.service.models.UserRegisterServiceModel;

public interface AuthValidationService {
    boolean isValid(UserRegisterServiceModel model);
}
