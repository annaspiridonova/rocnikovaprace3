package cz.gyarab3e.rocnikovaprace3.services;

public class ValidationException extends Exception{
    private final ValidationError validationError;


    public ValidationException(ValidationError validationError) {
        super();
        this.validationError = validationError;
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
