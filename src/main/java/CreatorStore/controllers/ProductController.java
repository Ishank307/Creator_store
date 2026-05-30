package CreatorStore.controllers;

import CreatorStore.Repositories.ProductRepository;
import CreatorStore.entities.Product;
import CreatorStore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product){
        return productService.createProduct(product);
    };
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id , @Valid @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return  productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id ){
        productService.deleteProduct(id);
    }
}
