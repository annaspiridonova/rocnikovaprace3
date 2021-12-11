package cz.gyarab3e.rocnikovaprace3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan("cz.gyarab3e.rocnikovaprace3.services")
public class Rocnikovaprace3Application {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    public static void main(String[] args) {
        SpringApplication.run(Rocnikovaprace3Application.class, args);
    }

}
