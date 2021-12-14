package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.GameUser;

public interface UserService {
    void signUp(GameUser user);
    void signIn(String username, String password);
    GameUser getUser(String username);
    void updateUser(GameUser user);
}
