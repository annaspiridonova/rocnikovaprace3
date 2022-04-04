package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.jpa.CellStatus;

public class BoardHolder {
    final private Long id;
    final private CellStatus[][] board;

    public Long getId() {
        return id;
    }


    public CellStatus[][] getBoard() {
        return board;
    }


    public BoardHolder(Long id, CellStatus[][] board) {
        this.id = id;
        this.board = board;
    }

}