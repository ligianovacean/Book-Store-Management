package bookStore.error;

public class InvalidSaleException extends Exception{

    private static final long serialVersionUID = 2L;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public InvalidSaleException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public InvalidSaleException() {
        super();
    }

}
