package api.product.repository;

import api.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    public Optional<Product> findProductByName(String name);

}
