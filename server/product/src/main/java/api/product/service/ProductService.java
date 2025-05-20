package api.product.service;

import api.product.dto.ProductRequest;
import api.product.model.Categories;
import api.product.model.Product;
import api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct( ProductRequest productRequest){

        try{
            Product product = new Product(productRequest.getName()
                    ,productRequest.getDescription(),
                    productRequest.getPrice(),
                    1,
                    Categories.valueOf(productRequest.getCategories()));

            return productRepository.save(product);
        }catch (Exception e){
            return null;
        }

    }
    public List<ProductRequest> getAllproduct(){

        try{
            List<Product> productsList = productRepository.findAll();
            List<ProductRequest> productResponseList = new ArrayList<>();

            for(Product product : productsList){
                productResponseList.add(new ProductRequest(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategories().toString()
                ));
            }

            return productResponseList;
        }catch (Exception error){
            return null;
        }

    }

    public void deleteAllProduct(){
       productRepository.deleteAll();
    }
}
