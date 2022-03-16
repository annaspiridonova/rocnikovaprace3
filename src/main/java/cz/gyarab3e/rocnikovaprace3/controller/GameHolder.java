package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.jpa.Game;
import cz.gyarab3e.rocnikovaprace3.jpa.GameUser;
import cz.gyarab3e.rocnikovaprace3.jpa.Status;

public class GameHolder {
    private Long id;
    private String code;
    private Status satus;
    private String currentPlayer;
    private Integer lastX;
    private Integer lastY;
    private MoveStatus lastMoveStatus;

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Integer getLastX() {
        return lastX;
    }

    public void setLastX(Integer lastX) {
        this.lastX = lastX;
    }

    public Integer getLastY() {
        return lastY;
    }

    public void setLastY(Integer lastY) {
        this.lastY = lastY;
    }

    public MoveStatus getLastMoveStatus() {
        return lastMoveStatus;
    }

    public void setLastMoveStatus(MoveStatus lastMoveStatus) {
        this.lastMoveStatus = lastMoveStatus;
    }

    public Status getSatus() {
        return satus;
    }

    public void setSatus(Status satus) {
        this.satus = satus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GameHolder(Game game){
        id =game.getId();
        code= game.getPlayingCode();
        satus=game.getStatus();
        if(game.getPlayingUser()!=null){
        currentPlayer=game.getPlayingUser().getUsername();}
        lastX= game.getLastX();
        lastY= game.getLastY();
        lastMoveStatus=game.getLastMoveStatus();
    }
    public GameHolder(Long id, String code, Status satus,String currentPlayer) {
        this.id = id;
        this.code = code;
        this.satus = satus;
        this.currentPlayer = currentPlayer;
    }

}
