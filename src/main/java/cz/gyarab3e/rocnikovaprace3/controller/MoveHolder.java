package cz.gyarab3e.rocnikovaprace3.controller;

public class MoveHolder {
    int x;
    int y;
    long id;

    public MoveHolder(int x, int y, long id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
