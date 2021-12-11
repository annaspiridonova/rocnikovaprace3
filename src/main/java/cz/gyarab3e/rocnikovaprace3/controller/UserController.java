package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.jpa.User;
import cz.gyarab3e.rocnikovaprace3.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserService userService,PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody UserHolder holder){
        User user = new User();
        user.setUsername(holder.getUsername());
        String password = holder.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userService.signUp(user);
    }
    @PostMapping("/signIn")
    public void signIn(@RequestBody UserHolder holder){
        userService.signIn(holder.getUsername(),holder.getPassword());
    }
////    public void signIn(String username, String password);
////    public User getUser(String username);
////    public void updateUser(User user);
}
