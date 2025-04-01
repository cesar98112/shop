package api.product.controller;


import api.product.dto.ProductDto;
import api.product.entity.Product;
import api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    

}
