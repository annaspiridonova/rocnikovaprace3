package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.controller.MoveStatus;
import cz.gyarab3e.rocnikovaprace3.jpa.*;

public interface GameService {
     Game startGameWithCode();
     Game joinGame(String playingCode) throws NoGameException;
     MoveStatus move(Long id, int x, int y) throws MoveExceptions;
     BaseGame getBaseGame(int id);
     Game getGame(Long id);
     Game getCurrentGame();
     BaseGame abandon(Long id);
     void saveBoard(Long id,CellStatus[][] board) throws ValidationException;

}
