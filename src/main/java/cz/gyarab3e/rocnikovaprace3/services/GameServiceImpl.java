package cz.gyarab3e.rocnikovaprace3.services;


import cz.gyarab3e.rocnikovaprace3.controller.MoveStatus;
import cz.gyarab3e.rocnikovaprace3.jpa.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {
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
        if (gameOptional.isPresent()) {
            Authentication user2 = SecurityContextHolder.getContext().getAuthentication();
            Game game = gameOptional.get();
            game.setStatus(Status.waitingForBoards);
            game.setUser2((GameUser) user2.getPrincipal());
            gameRepository.save(game);
            return game;
        } else {
            //todo
            return null;
        }
    }

    @Override
    public void saveBoard(Long id, CellStatus[][] board) throws ValidationException{
        boolean validBoard = boardValidaton(board);
        if(!validBoard){
            throw new ValidationException();
        }
        Game game = getGame(id);
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        if (user.getName().equals(game.getUser1().getUsername())) {
            game.setCellStatuses1(board);
        } else if (user.getName().equals(game.getUser2().getUsername())) {
            game.setCellStatuses2(board);
        } else {
            //todo
        }
        if (game.getCellStatuses1() != null && game.getCellStatuses2() != null) {
            game.setStatus(Status.running);
        }
        gameRepository.save(game);

    }

    @Override
    public MoveStatus move(Long id, int x, int y) {
        Game game = getGame(id);
        CellStatus[][] cellStatuses = null;
        MoveStatus moveStatus = MoveStatus.shot;
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        if (user.getName().equals(game.getUser1().getUsername())) {
            cellStatuses = game.getCellStatuses2();
        } else if (user.getName().equals(game.getUser2().getUsername())) {
            cellStatuses = game.getCellStatuses1();
        } else {
            //todo
        }
        CellStatus status = cellStatuses[x][y];
        switch (status) {
            case shot -> throw new RuntimeException(); //todo

            case blank -> {
                cellStatuses[x][y] = CellStatus.unavailable;
                moveStatus = MoveStatus.missed;
            }
            case filled -> {
                if (wasItTheWholeShip(x, y, cellStatuses)) {
                    unavaiableing(x, y, cellStatuses);
                    if (anyShipsLeft(cellStatuses)) {
                        cellStatuses[x][y] = CellStatus.shot;
                        moveStatus = MoveStatus.wholeShip;
                    } else {
                        moveStatus = MoveStatus.won;
                    }
                } else {
                    cellStatuses[x][y] = CellStatus.shot;
                    moveStatus = MoveStatus.shot;
                }
            }

            case unavailable -> throw new RuntimeException(); //todo

        }
        game.setUpdatedate(new Date());
        gameRepository.save(game);
        return moveStatus;

    }

    //    private boolean BoardValidation(CellStatus[][] cellStatuses){
//
//
//    }
    private boolean anyShipsLeft(CellStatus[][] cellStatuses) {
        for (int row = 0; row < cellStatuses.length; row++) {
            for (int col = 0; col < cellStatuses[row].length; col++) {
                if (cellStatuses[row][col] == CellStatus.filled) {
                    return true;
                }
            }
        }
        return false;
    }
    private void markIfExist(int x, int y, CellStatus[][] cellStatus) {
        if (x > 0 && x < GameConstants.CELL_SIZE && y > 0 && y < GameConstants.CELL_SIZE && cellStatus[x][y] != CellStatus.shot) {
            cellStatus[x][y] = CellStatus.unavailable;
        }
    }

    private int howManyInTherow(int x, int y, CellStatus[][] board) {
        int returning = 1;
        int a = 1;
        int b = 1;
        int c = 1;
        int d = 1;
        if (x - a >=0){
        while (board[x - a][y] == CellStatus.filled && !diagnal(x - a, y, board)) {
            returning += 1;
            board[x - a][y] = CellStatus.unavailable;
            a--;
            if(returning>a){
                returning -= 1000;
            }
        }
        }
        if(x+b<GameConstants.CELL_SIZE){
        while (board[x + b][y] == CellStatus.filled && !diagnal(x + b, y, board)) {
            returning += 1;
            board[x + b][y] = CellStatus.unavailable;
            b++;
            if(returning>b){
                returning -= 1000;
            }
        }
        }
        if(x+c<GameConstants.CELL_SIZE){
        while (board[x][y + c] == CellStatus.filled && !diagnal(x, y + c, board)) {
            returning += 1;
            board[x][y + c] = CellStatus.unavailable;
            c++;
            if(returning>c){
                returning -= 1000;
            }
        }
        }
        if(y-d>=0){
        while (board[x][y - d] == CellStatus.filled && !diagnal(x, y - d, board)) {
            returning += 1;
            board[x][y - d] = CellStatus.unavailable;
            d--;
            if(returning>d){
                returning -= 1000;
            }
        }
        }
        return returning;
    }

    private boolean diagnal(int x, int y, CellStatus[][] board) {
        if (x - 1 >= 0 && y  -1>=0) {
            if (board[x - 1][y - 1] == CellStatus.filled) {
                return true;
            }
        }
        if (x + 1 < GameConstants.CELL_SIZE && y + 1 < GameConstants.CELL_SIZE) {
            if (board[x + 1][y + 1] == CellStatus.filled) {
                return true;
            }
        }
        if (x - 1 >= 0 && y + 1 < GameConstants.CELL_SIZE) {
            if (board[x - 1][y + 1] == CellStatus.filled) {
                return true;
            }
        }
        if (x + 1 < GameConstants.CELL_SIZE && y - 1 >=  0) {

            if (board[x + 1][y - 1] == CellStatus.filled) {
                return true;
            }
        }
        return false;
    }

    public boolean boardValidaton(CellStatus[][] oriBoard) {
        boolean oneChecked = false, twoChecked = false, threeChecked = false, fourChecked = false, fiveChecked = false;
        int filledCells=0;
        CellStatus[][] board = Arrays.stream(oriBoard)
                .map(a -> Arrays.copyOf(a, oriBoard.length + 1))
                .toArray(CellStatus[][]::new);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == CellStatus.filled) {
                    filledCells += 1;
                    if (diagnal(i, j, board)) {
                        return false;
                    }
                    int hmitr = howManyInTherow(i, j, board);
                    if (hmitr < 0 || hmitr > 5) {
                        return false;
                    }

                    if (hmitr == 1) {
                        if (!oneChecked) {
                            oneChecked = true;
                        } else {
                            return false;
                        }
                    }
                    if (hmitr== 2) {
                        if (!twoChecked) {
                            twoChecked = true;
                            filledCells+=1;
                        } else {
                            return false;
                        }
                    }
                    if (hmitr == 3) {
                        if (!threeChecked) {
                            threeChecked = true;
                            filledCells+=2;
                        } else {
                            return false;
                        }
                    }
                    if (hmitr == 4) {
                        if (!fourChecked) {
                            fourChecked = true;
                            filledCells+=3;
                        } else {
                            return false;
                        }
                    }
                    if (hmitr == 5) {
                        if (!fiveChecked) {
                            fiveChecked = true;
                            filledCells+=4;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        if (filledCells != 15 || !oneChecked || !fiveChecked || !twoChecked || !threeChecked || !fourChecked) {
            return false;
        } else {
            return true;
        }
    }

    private void unavaiableing(int x, int y, CellStatus[][] cellStatus) {
        int a = x;
        int b = y;

        do {
            markIfExist(a, y - 1, cellStatus);
            markIfExist(a, y + 1, cellStatus);
            a++;
        } while (a < cellStatus.length && cellStatus[a][y] == CellStatus.shot);
        markIfExist(a, y, cellStatus);
        markIfExist(a, y - 1, cellStatus);
        markIfExist(a, y + 1, cellStatus);

        a = x;
        do {
            markIfExist(a, y - 1, cellStatus);
            markIfExist(a, y + 1, cellStatus);
            a--;
        } while (a > -1 && a < cellStatus.length && cellStatus[a][y] == CellStatus.shot);
        markIfExist(a, y, cellStatus);
        markIfExist(a, y - 1, cellStatus);
        markIfExist(a, y + 1, cellStatus);

        do {
            markIfExist(x, b - 1, cellStatus);
            markIfExist(x, b + 1, cellStatus);
            b++;
        } while (b < cellStatus.length && cellStatus[x][b] == CellStatus.shot);
        markIfExist(x, b, cellStatus);
        markIfExist(x - 1, b, cellStatus);
        markIfExist(x + 1, b, cellStatus);

        b = y;
        do {
            markIfExist(x, b - 1, cellStatus);
            markIfExist(x, b + 1, cellStatus);
            b--;
        } while (b > -1 && b < cellStatus.length && cellStatus[x][b] == CellStatus.shot);
        markIfExist(x, b, cellStatus);
        markIfExist(x - 1, b, cellStatus);
        markIfExist(x + 1, b, cellStatus);
    }


    private boolean wasItTheWholeShip(int x, int y, CellStatus[][] cellStatus) {
        if (x + 1 < GameConstants.CELL_SIZE && cellStatus[x + 1][y] == CellStatus.filled) {
            return false;
        }
        if (y + 1 < GameConstants.CELL_SIZE && cellStatus[x][y + 1] == CellStatus.filled) {
            return false;
        }
        if (x - 1 > 0 && cellStatus[x - 1][y] == CellStatus.filled) {
            return false;
        }
        if (y - 1 > 0 && cellStatus[x][y - 1] == CellStatus.filled) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public BaseGame getBaseGame(int id) {
        return null;
    }


    @Override
    public Game getGame(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    @Override
    public Game getCurrentGame() {
        return null;
    }

    @Override
    public BaseGame abandon(Long id) {
        return null;
    }


//    private CellStatus getMyBoard(Long id) {
//        Game game =getGame(id);
//        Authentication user = SecurityContextHolder.getContext().getAuthentication();
//        if (user.getName().equals(game.getUser1().getUsername())){
//            game.setCellStatuses1();
//
//        return null;
//    }
//
//    private CellStatus getSecondBoard(Long id) {
//        return null;
//    }
}