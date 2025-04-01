package api.product.controller;


import api.product.dto.ProductDto;
import api.product.entity.Product;
import api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class productController {

    @Autowired
    private ProductService productService;


    @PostMapping("/save")
    public ResponseEntity<String> saveProductController(@RequestBody ProductDto productRequest){
        Product productResponse = productService.saveProduct(productRequest);
        if(productResponse == null){
            return new ResponseEntity<>("El producto ya existe", HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<>("El producto se guardo correctamente", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProductController(){

        List<ProductDto> responseProductList = productService.getAllProduct();

        if(!responseProductList.isEmpty()){
            return new ResponseEntity<>(responseProductList, HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }



}
