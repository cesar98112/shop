package api.product.controller;


import api.product.dto.ProductRequest;
import api.product.model.Product;
import api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveProductController (@RequestBody ProductRequest productRequest){

        Product product = productService.saveProduct(productRequest);

        if(product != null){
            return new ResponseEntity<Object>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getAll")
    public ResponseEntity<Object> getAllProductController(){
        List<ProductRequest> productList = productService.getAllproduct();

        if(productList != null){
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
