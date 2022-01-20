package cz.gyarab3e.rocnikovaprace3.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

     Optional<Game> findByPlayingCode(String playingCode);
}
