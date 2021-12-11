package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public void signUp(User user) {

    }

    @Override
    public void signIn(String username, String password) {

    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }
}
