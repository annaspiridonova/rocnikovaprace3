package cz.gyarab3e.rocnikovaprace3.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

// user repository
public interface UserRepository extends JpaRepository<GameUser, String> {
}

