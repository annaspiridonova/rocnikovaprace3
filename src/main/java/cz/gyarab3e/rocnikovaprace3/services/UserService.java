package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void signUp(User user, String password);
    public void signIn(String username, String password);
    public User getUser(String username);
    public void updateUser(User user);
}
