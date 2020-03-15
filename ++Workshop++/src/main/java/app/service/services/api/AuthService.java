package app.service.services.api;

import app.service.models.UserAuthenticatedServiceModel;
import app.service.models.UserLoginServiceModel;
import app.service.models.UserRegisterServiceModel;

public interface AuthService {

    void register(UserRegisterServiceModel serviceModel);

    UserAuthenticatedServiceModel login(UserLoginServiceModel serviceModel);
}
