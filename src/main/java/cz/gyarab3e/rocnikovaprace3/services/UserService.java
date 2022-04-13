package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.GameUser;
import org.springframework.security.core.userdetails.UserDetailsService;

// user service
public interface UserService extends UserDetailsService {
    void signUp(GameUser user);

    GameUser getUser(String username);

}
