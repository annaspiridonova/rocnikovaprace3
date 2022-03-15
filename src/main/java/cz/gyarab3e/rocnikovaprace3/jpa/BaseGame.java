package cz.gyarab3e.rocnikovaprace3.jpa;

import javax.persistence.*;

@MappedSuperclass
public class BaseGame {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    @ManyToOne
    private GameUser playingUser;
    private String playingCode;
    private Status status;
    @ManyToOne
    private GameUser winner;

    public GameUser getPlayingUser() {
        return playingUser;
    }

    public void setPlayingUser(GameUser playingUser) {
        this.playingUser = playingUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayingCode() {
        return playingCode;
    }

    public void setPlayingCode(String playingCode) {
        this.playingCode = playingCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public GameUser getWinner() {
        return winner;
    }

    public void setWinner(GameUser winner) {
        this.winner = winner;
    }
}
