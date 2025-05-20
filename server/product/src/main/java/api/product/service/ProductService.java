package api.product.service;

import api.product.controller.ProductController;
import api.product.dto.ProductRequest;
import api.product.model.Categories;
import api.product.model.Product;
import api.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Product productUpdate(ProductRequest productRequest , String productName){
        try{
            Product product = productRepository.findByName(productName)
                    .orElseThrow(()-> new RuntimeException("no existe el producto"));

            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setCategories(Categories.valueOf(productRequest.getCategories()));
            return productRepository.save(product);


        }catch (Exception error){
            return null;
        }

    }

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

    public boolean addStockProduct(String name, int quantity){
        try{
            Product product = productRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("producto no encontrado"));

            product.setQuantity(product.getQuantity()+quantity);

            productRepository.save(product);

            return true;
        }catch (Exception err){

            return false;
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
