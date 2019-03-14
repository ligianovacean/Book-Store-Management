package bookStore.dto;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {
    @Size(min = 8, message = "Username too short.\n")
    public String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least 8 characters, a digit and a special character.\n")
    public String password;
}
