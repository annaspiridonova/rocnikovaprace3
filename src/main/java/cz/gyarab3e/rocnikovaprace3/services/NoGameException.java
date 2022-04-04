package cz.gyarab3e.rocnikovaprace3.services;

public class NoGameException extends Exception {
    private final NoGameError noGameError;

    public NoGameException(NoGameError noGameError) {
        this.noGameError = noGameError;
    }

    public NoGameError getNoGameError() {
        return noGameError;
    }
}
