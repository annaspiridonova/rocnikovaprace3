package cz.gyarab3e.rocnikovaprace3.jpa;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameUser {
    @Id
    private String username;
    private String password;
    private int winningRate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWinningRate() {
        return winningRate;
    }

    public void setWinningRate(int winningRate) {
        this.winningRate = winningRate;
    }
}
