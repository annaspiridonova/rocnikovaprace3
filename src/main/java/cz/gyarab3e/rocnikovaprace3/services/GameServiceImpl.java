package cz.gyarab3e.rocnikovaprace3.services;


import cz.gyarab3e.rocnikovaprace3.jpa.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService{
    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game startGameWithCode() {
        Game game = new Game();
        game.setPlayingCode(RandomStringUtils.randomAlphanumeric(6).toUpperCase());
        game.setStatus(Status.waiting);
        game = gameRepository.save(game);
        return game;
    }

    @Override
    public void joinGame(String playingCode) {

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
