package app.service.services.api;

import app.service.models.user.UserAuthenticatedServiceModel;
import app.service.models.user.UserLoginServiceModel;
import app.service.models.user.UserRegisterServiceModel;

public interface AuthService {

    void register(UserRegisterServiceModel serviceModel);

    UserAuthenticatedServiceModel login(UserLoginServiceModel serviceModel);
}
