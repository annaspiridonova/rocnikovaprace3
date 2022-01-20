package cz.gyarab3e.rocnikovaprace3.controller;

public class GameHolder {
    private Long Id;
    private String code;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GameHolder(Long id, String code) {
        Id = id;
        this.code = code;
    }
}
