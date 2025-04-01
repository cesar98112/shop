package api.product.service;

import api.product.dto.ProductDto;
import api.product.entity.Category;
import api.product.entity.Product;
import api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(ProductDto productRequest){
        if(productRepository.findProductByName(productRequest.getName()).isEmpty()){

            Product product = new Product(productRequest.getName(),
                    productRequest.getDescription(),
                    productRequest.getPrice(),
                    Category.valueOf(productRequest.getCategory()),
                    1);

            return productRepository.save(product);
        }

        return null;


    }

    public List<ProductDto> getAllProduct(){
        List<Product> productList = productRepository.findAll();

        List<ProductDto> responseProductList = new ArrayList<>();

        if(!productList.isEmpty()){
            for(int i = 0; i<productList.size(); i++){
                Product product = productList.get(i);

                ProductDto productDto = new ProductDto(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory().toString()
                );
                responseProductList.add(productDto);
            }
            return responseProductList;
        }
        return null;
    }

}
