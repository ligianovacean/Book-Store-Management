package bookStore.error;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ErrorCollector {

    public static List<String> constructErrors(BindingResult result) {
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errorMessages.add(fieldError.getDefaultMessage());
        }
        return errorMessages;
    }

}
