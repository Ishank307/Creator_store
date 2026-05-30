package CreatorStore.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderRequest {
    @NotBlank(message = "customer Name is required")
    private String customerName;
    @NotBlank(message = "customer Email is required")
    @Email(message = "Please enter valid email address")
    private String customerEmail;

    @Valid
    @NotEmpty(message = "Order must contain atleast 1 item")
    private List<OrderItemRequest> items;
}
