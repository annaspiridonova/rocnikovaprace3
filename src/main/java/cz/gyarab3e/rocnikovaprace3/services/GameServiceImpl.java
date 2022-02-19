package cz.gyarab3e.rocnikovaprace3.services;


import cz.gyarab3e.rocnikovaprace3.controller.MoveStatus;
import cz.gyarab3e.rocnikovaprace3.jpa.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void saveBoard(Long id, CellStatus[][] board) {
        Game game = getGame(id);
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        if (user.getName().equals(game.getUser1().getUsername())) {
            game.setCellStatuses1(board);
        } else if (user.getName().equals(game.getUser2().getUsername())) {
            game.setCellStatuses2(board);
        } else {
            //todo
        }
        if(game.getCellStatuses1()!=null && game.getCellStatuses2()!=null){
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

            case blank -> {cellStatuses[x][y]=CellStatus.unavailable;
                moveStatus= MoveStatus.missed;
            }
            case filled -> {
                if (wasItTheWholeShip(x, y, cellStatuses)) {
                    unavaiableing(x, y, cellStatuses);
                    if(anyShipsLeft(cellStatuses)){
                        cellStatuses[x][y]=CellStatus.shot;
                        moveStatus=MoveStatus.wholeShip;
                    }else {
                        moveStatus= MoveStatus.won;}
                }else{
                    cellStatuses[x][y]=CellStatus.shot;
                    moveStatus=MoveStatus.shot;
                }break;
            }

            case unavailable -> throw new RuntimeException(); //todo

        }
        gameRepository.saveAndFlush(game);
        return moveStatus;

    }
//    private boolean BoardValidation(CellStatus[][] cellStatuses){
//
//
//    }
    private boolean anyShipsLeft(CellStatus[][] cellStatuses){
        for (int row = 0; row < cellStatuses.length; row++) {
            for (int col = 0; col < cellStatuses[row].length; col++) {
                if (cellStatuses[row][col]==CellStatus.filled) {
                    return true;
                }
            }
        }return false;
    }

    public Boolean boardValidaton(CellStatus[][] board){
        
        return true;
    }
    private void markIfExist(int x, int y, CellStatus[][] cellStatus){
        if(x>0&&x<GameConstants.CELL_SIZE&&y>0&&y<GameConstants.CELL_SIZE&&cellStatus[x][y]!=CellStatus.shot){
            cellStatus[x][y]=CellStatus.unavailable;
        }
    }
    private void unavaiableing(int x, int y, CellStatus[][] cellStatus) {
        int a = x;
        int b = y;

        do{
            markIfExist(a,y-1,cellStatus);
            markIfExist(a,y+1,cellStatus);
            a++;
        }while (a< cellStatus.length && cellStatus[a][y]==CellStatus.shot);
        markIfExist(a,y,cellStatus);
        markIfExist(a,y-1,cellStatus);
        markIfExist(a,y+1,cellStatus);

        a=x;
        do{
            markIfExist(a,y-1,cellStatus);
            markIfExist(a,y+1,cellStatus);
            a--;
        }while (a>-1&&a< cellStatus.length && cellStatus[a][y]==CellStatus.shot);
        markIfExist(a,y,cellStatus);
        markIfExist(a,y-1,cellStatus);
        markIfExist(a,y+1,cellStatus);

        do{
            markIfExist(x,b-1,cellStatus);
            markIfExist(x,b+1,cellStatus);
            b++;
        }while (b< cellStatus.length && cellStatus[x][b]==CellStatus.shot);
        markIfExist(x,b,cellStatus);
        markIfExist(x-1,b,cellStatus);
        markIfExist(x+1,b,cellStatus);

        b=y;
        do{
            markIfExist(x,b-1,cellStatus);
            markIfExist(x,b+1,cellStatus);
            b--;
        }while (b>-1&&b< cellStatus.length && cellStatus[x][b]==CellStatus.shot);
        markIfExist(x,b,cellStatus);
        markIfExist(x-1,b,cellStatus);
        markIfExist(x+1,b,cellStatus);
    }


    private boolean wasItTheWholeShip(int x, int y, CellStatus[][] cellStatus) {
        if (x + 1 < GameConstants.CELL_SIZE && cellStatus[x + 1][y]==CellStatus.filled) {
            return false;
        }
        if (y + 1 < GameConstants.CELL_SIZE && cellStatus[x][y + 1]==CellStatus.filled) {
            return false;
        }
        if (x - 1 >0 && cellStatus[x - 1][y]==CellStatus.filled) {
            return false;
        }
        if (y - 1 >0 && cellStatus[x][y - 1]==CellStatus.filled) {
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