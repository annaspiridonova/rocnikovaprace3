package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.User;
import org.springframework.stereotype.Service;

public interface UserService {
    void signUp(User user);
    void signIn(String username, String password);
    User getUser(String username);
    void updateUser(User user);
}
