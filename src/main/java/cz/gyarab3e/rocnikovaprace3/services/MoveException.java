package cz.gyarab3e.rocnikovaprace3.services;

public class MoveException extends Exception {
    private final MoveError moveError;

    public MoveException(MoveError moveError) {
        super();
        this.moveError = moveError;
    }

    public MoveError getMoveError() {
        return moveError;
    }
}
