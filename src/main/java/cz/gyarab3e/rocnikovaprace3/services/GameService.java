package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.*;

import java.util.List;

public interface GameService {
     Game startGameWithCode();
     Game joinGame(String playingCode);
     Move makeMove(int id, int x,int y);
     Move getLastMove(int id);
     List<Move> getMoves(int id);
     BaseGame getBaseGame(int id);
     Game getGame(int id);
     Game getCurrentGame();
     BaseGame abandon(int id);
}
