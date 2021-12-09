package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.*;

import java.util.List;

public interface GameService {
    public Game startGameWithCode();
    public void joinGame(String playingCode);
    public Move makeMove(int id, int x,int y);
    public Move getLastMove(int id);
    public List<Move> getMoves(int id);
    public BaseGame getBaseGame(int id);
    public Game getGame(int id);
    public Game getCurrentGame();
    public BaseGame abandon(int id);
}
