package bookStore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class SaleDTO {

    @Size(min = 1, message = "Title cannot be null.\n")
    public String title;
    public int authorId;
    @Min(value = 0, message = "Quantity cannot be a negative number.\n")
    public int quantity;
    public int initialQuantity;
    public int price;

}
