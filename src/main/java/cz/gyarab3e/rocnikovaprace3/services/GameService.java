package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.controller.MoveStatus;
import cz.gyarab3e.rocnikovaprace3.jpa.*;

import java.util.Optional;

// methods which handle game logic
public interface GameService {
    Game startGameWithCode();

    Game joinGame(String playingCode) throws NotFoundException, ValidationException;

    MoveStatus move(Long id, int x, int y) throws MoveException, NotFoundException;

    Optional<Game> getGame(Long id);

    BaseGame abandon(Long id) throws NotFoundException, AccessDeniedExceptions;

    void saveBoard(Long id, CellStatus[][] board) throws ValidationException, NotFoundException;

    CellStatus[][] returnUsersBoard(Long id, String username) throws NotFoundException;

    CellStatus[][] returnOpponentsBoard(Long id, String username) throws NotFoundException;

    int getWinningRate(String username);


}
