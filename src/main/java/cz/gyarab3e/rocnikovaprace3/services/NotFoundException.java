package cz.gyarab3e.rocnikovaprace3.services;

//Not Found Exception
public class NotFoundException extends Exception {
    private final NotFoundError notFoundError;

    public NotFoundException(NotFoundError notFoundError) {
        this.notFoundError = notFoundError;
    }

    public NotFoundError getNoGameError() {
        return notFoundError;
    }
}

