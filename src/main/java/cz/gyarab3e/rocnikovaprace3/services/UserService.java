package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.GameUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void signUp(GameUser user);
    GameUser getUser(String username);
    void updateUser(GameUser user);
}
