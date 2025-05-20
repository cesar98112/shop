package api.product.controller;


import api.product.dto.ProductRequest;
import api.product.model.Product;
import api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ResponseEntity<Object> saveProductController (@RequestBody ProductRequest productRequest){

        Product product = productService.saveProduct(productRequest);

        if(product != null){
            return new ResponseEntity<Object>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("error en guardar", HttpStatus.CONFLICT);
        }

    }
}
