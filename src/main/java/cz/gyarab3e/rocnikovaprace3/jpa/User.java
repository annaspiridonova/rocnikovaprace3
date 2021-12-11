package cz.gyarab3e.rocnikovaprace3.jpa;

public class User {
    private String username;
    private String password;
    private byte[] avatar;
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public int getWinningRate() {
        return winningRate;
    }

    public void setWinningRate(int winningRate) {
        this.winningRate = winningRate;
    }
}
