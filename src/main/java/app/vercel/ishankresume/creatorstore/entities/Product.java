package app.vercel.ishankresume.creatorstore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    private String description;

    @NotNull(message = "category is required")
    private String category;

    @NotNull(message = "Prive is required")
    @DecimalMin(value="0.0",inclusive=false,message = "price cant be less than zero")
    private BigDecimal price;

    @NotNull(message = "stock quantity is required")
    @Min(value=0,message = "Stock cannot be less than 0")
    @Column(name="stock_quantity",nullable = false)
    private Integer stockQuantity;

    //TODO:relations
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
