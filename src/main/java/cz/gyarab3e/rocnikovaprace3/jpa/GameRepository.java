package cz.gyarab3e.rocnikovaprace3.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

     Optional<Game> findByPlayingCode(String playingCode);
     @Modifying
     @Transactional
     @Query("UPDATE Game set status= :newStatus where status=:oldStatus and updatedate<:date")
     public int abandonGames(@Param("date") java.sql.Date date,@Param("oldStatus") Status oldStatus,@Param("newStatus") Status newStatus);
}
