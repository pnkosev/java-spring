package app.service.services.api;

import app.service.models.user.UserDetailsServiceModel;

public interface UserService {

    UserDetailsServiceModel getUserByName(String username);
}
