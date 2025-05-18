package api.product.service;

import api.product.dto.ProductRequest;
import api.product.model.Categories;
import api.product.model.Product;
import api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct( ProductRequest productRequest){
        Product product = new Product(productRequest.getName()
                ,productRequest.getDescription(),
                productRequest.getPrice(),
                1,
                Categories.valueOf(productRequest.getCategories()));

        return productRepository.save(product);
    }
}
