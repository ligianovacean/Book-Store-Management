package bookStore.dto;

import javax.validation.constraints.Size;

public class AuthorDTO {
    @Size(min=1, message = "Name cannot be null.\n")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
