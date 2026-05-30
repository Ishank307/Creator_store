package CreatorStore.service;

import CreatorStore.Repositories.ProductRepository;
import CreatorStore.entities.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    public Product createProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    };

    public Product updateProduct( Long id , Product product){
        Product existingProduct =productRepository.findById(id)
                .orElseThrow(()->(new RuntimeException("Product id not found"+ id)));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStockQuantity(product.getStockQuantity());


        return productRepository.save(existingProduct);

    }


    public List<Product> getProducts(){
        return productRepository.findAll();
    }


    public Product getProductById( Long id){

        return productRepository.findById(id)
                .orElseThrow(()->(
                        new RuntimeException("Product with  given id not found" + id)
                        ));
    }


    public void deleteProduct(Long id ){
        productRepository.deleteById(id);
    }
}
