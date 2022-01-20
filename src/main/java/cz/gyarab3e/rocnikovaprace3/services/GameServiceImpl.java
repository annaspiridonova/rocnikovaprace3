package cz.gyarab3e.rocnikovaprace3.services;


import cz.gyarab3e.rocnikovaprace3.jpa.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game startGameWithCode() {
        Game game = new Game();
        game.setPlayingCode(RandomStringUtils.randomAlphanumeric(6).toUpperCase());
        game.setStatus(Status.waiting);
        Authentication user1 = SecurityContextHolder.getContext().getAuthentication();
        game.setUser1((GameUser) user1.getPrincipal());
        game = gameRepository.save(game);
        return game;
    }

    @Override
    public Game joinGame(String playingCode) {
        Optional<Game> gameOptional = gameRepository.findByPlayingCode(playingCode);
        if(gameOptional.isPresent()){
            Authentication user2 = SecurityContextHolder.getContext().getAuthentication();
            Game game =gameOptional.get();
            game.setUser2((GameUser) user2.getPrincipal());
            gameRepository.save(game);
            return game;
        }else{
            //to do
            return null;
        }
    }

    @Override
    public Move makeMove(int id, int x, int y) {
        return null;
    }

    @Override
    public Move getLastMove(int id) {
        return null;
    }

    @Override
    public List<Move> getMoves(int id) {
        return null;
    }

    @Override
    public BaseGame getBaseGame(int id) {
        return null;
    }

    @Override
    public Game getGame(int id) {
        return null;
    }

    @Override
    public Game getCurrentGame() {
        return null;
    }

    @Override
    public BaseGame abandon(int id) {
        return null;
    }
}
