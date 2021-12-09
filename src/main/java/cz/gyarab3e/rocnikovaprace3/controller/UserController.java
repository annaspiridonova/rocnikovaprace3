package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.jpa.User;
import cz.gyarab3e.rocnikovaprace3.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public void signUp(User user, String password)(@RequestBody){

    }
////    public void signIn(String username, String password);
////    public User getUser(String username);
////    public void updateUser(User user);
}
