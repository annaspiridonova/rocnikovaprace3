package cz.gyarab3e.rocnikovaprace3.services;

import cz.gyarab3e.rocnikovaprace3.jpa.Board;
import cz.gyarab3e.rocnikovaprace3.jpa.CellStatus;

public interface BoardService {
    public Board createBoard(int gameId, CellStatus[][] cellStatus);
    public Board getBoard(int gameId);

}
