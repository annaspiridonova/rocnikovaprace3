package cz.gyarab3e.rocnikovaprace3.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cz.gyarab3e.rocnikovaprace3.jpa.GameUser;
import cz.gyarab3e.rocnikovaprace3.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static cz.gyarab3e.rocnikovaprace3.controller.SecurityConstants.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserHolder holder) {
        GameUser user = new GameUser();
        user.setUsername(holder.getUsername());
        String password = holder.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        try {
            userService.signUp(user);


            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    holder.getUsername(), holder.getPassword()
                            )
                    );


            String token = JWT.create()
                    .withSubject(holder.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));

            return ResponseEntity.ok()
                    .header(

                            holder.getUsername()
                    )
                    .body(
                            token);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody UserHolder holder) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    }

    @PostMapping("/signIn")
    @ResponseBody
    public ResponseEntity<String> signIn(@RequestBody UserHolder holder) {
        try {

            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    holder.getUsername(), holder.getPassword()
                            )
                    );


            String token = JWT.create()
                    .withSubject(holder.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));

            return ResponseEntity.ok()
                    .header(

                            holder.getUsername()
                    )
                    .body(
                            token);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
