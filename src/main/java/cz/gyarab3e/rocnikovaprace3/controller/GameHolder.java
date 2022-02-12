package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.jpa.Game;
import cz.gyarab3e.rocnikovaprace3.jpa.Status;

public class GameHolder {
    private Long id;
    private String code;
    private Status satus;


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
    }
    public GameHolder(Long id, String code, Status satus) {
        this.id = id;
        this.code = code;
        this.satus = satus;
    }
}
