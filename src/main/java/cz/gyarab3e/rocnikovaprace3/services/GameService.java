package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.controller.MoveStatus;
import cz.gyarab3e.rocnikovaprace3.jpa.*;

import java.util.Optional;

public interface GameService {
    Game startGameWithCode();

    Game joinGame(String playingCode) throws NoGameException, ValidationException;

    MoveStatus move(Long id, int x, int y) throws MoveException, NoGameException;

    Optional<Game> getGame(Long id);

    BaseGame abandon(Long id) throws NoGameException, AccessDeniedExceptions;

    void saveBoard(Long id, CellStatus[][] board) throws ValidationException, NoGameException;

    CellStatus[][] returnUsersBoard(Long id, String username) throws NoGameException;

    CellStatus[][] returnOpponentsBoard(Long id, String username) throws NoGameException;

    int getWinningRate(String username);


}
