package bookStore.error;

import java.util.List;

public class ValidationException extends Exception{

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public ValidationException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ValidationException() {
        super();
    }

}
