package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.GameUser;
import cz.gyarab3e.rocnikovaprace3.jpa.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(GameUser user) {
        userRepository.save(user);
    }

    @Override
    public void signIn(String username, String password) {

    }

    @Override
    public GameUser getUser(String username) {
        return null;
    }

    @Override
    public void updateUser(GameUser user) {

    }
}
