package api.product.controller;


import api.product.dto.ProductRequest;
import api.product.model.Product;
import api.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/save")
    public ResponseEntity<Object> saveProductController (@RequestBody ProductRequest productRequest){

        Product product = productService.saveProduct(productRequest);

        if(product != null){
            return new ResponseEntity<Object>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{name}")
    public ResponseEntity<String> updateProductController(@PathVariable(value = "name") String productName,@RequestBody ProductRequest productRequest){
        Product product = productService.productUpdate(productRequest,productName);


        if(product != null){
            return new ResponseEntity<>("producto actualizado con exito", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al actualizar el producto", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addStock/{name}/{quantity}")
    public ResponseEntity<String> addStockController(
            @PathVariable(value = "name") String productName,
            @PathVariable(value = "quantity") int quantity){

        if(productService.addStockProduct(productName,quantity)){
            return new ResponseEntity<>("stock actualizado con exito",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Erro al actualizar el stock",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllProductController(){
        List<ProductRequest> productList = productService.getAllproduct();

        if(productList != null){
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
