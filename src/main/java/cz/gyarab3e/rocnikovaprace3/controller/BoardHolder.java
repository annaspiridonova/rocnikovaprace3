package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.jpa.CellStatus;

public class BoardHolder {
    Long id;
    CellStatus[][] board;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CellStatus[][] getBoard() {
        return board;
    }

    public void setBoard(CellStatus[][] board) {
        this.board = board;
    }

    public BoardHolder(Long id, CellStatus[][] board) {
        this.id = id;
        this.board = board;
    }

}
