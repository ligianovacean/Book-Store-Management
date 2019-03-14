package bookStore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class BookDto {
    @Size(min = 1, message = "Title cannot be null.\n")
    public String title;
    @Pattern(regexp = "[a-zA-Z]+", message = "Genre should contain only letters.\n")
    @Size(min = 1)
    public String genre;
    @Min(value = 0, message = "Quantity cannot be a negative number.\n")
    public int quantity;
    @Min(value = 0, message = "Price cannot be a negative number.\n")
    public int price;
    public int authorId;
}
