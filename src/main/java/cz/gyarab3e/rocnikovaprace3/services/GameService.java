package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.controller.BoardHolder;
import cz.gyarab3e.rocnikovaprace3.controller.MoveStatus;
import cz.gyarab3e.rocnikovaprace3.jpa.*;

import java.util.Optional;

public interface GameService {
     Game startGameWithCode();
     Game joinGame(String playingCode) throws NoGameException, ValidationException;
     MoveStatus move(Long id, int x, int y) throws MoveExceptions, NoGameException;
     BaseGame getBaseGame(int id);
     Optional<Game> getGame(Long id);
     Game getCurrentGame();
     BaseGame abandon(Long id) throws NoGameException, AccessDeniedExceptions;
     void saveBoard(Long id,CellStatus[][] board) throws ValidationException, NoGameException;
     CellStatus[][] returnUsersBoard(Long id, String username) throws NoGameException;
     CellStatus[][] returnOpponentsBoard(Long id,String username) throws NoGameException;
     int getWinningRate(String username);


}
