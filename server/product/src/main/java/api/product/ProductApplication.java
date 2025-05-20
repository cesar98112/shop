package api.product;

import api.product.dto.ProductRequest;
import api.product.model.Product;
import api.product.repository.ProductRepository;
import api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner{



	@Autowired
	private ProductService productService;

	public static void main(String[] args) {

		SpringApplication.run(ProductApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		List<ProductRequest> listProduct = productService.getAllproduct();

		if(!listProduct.isEmpty()){
			productService.deleteAllProduct();
		}

		ProductRequest product1 = new ProductRequest("iPhone 14 Pro","Smartphone de alta gama con cámara triple y pantalla OLED de 6.1 pulgadas.",1199.99,"MOBILES");
		ProductRequest product2 = new ProductRequest("Samsung Galaxy S22","Teléfono Android con potente procesador y pantalla AMOLED.",999.99,"MOBILES");
		ProductRequest product3 = new ProductRequest("Aspiradora Dyson V11","Aspiradora inalámbrica potente para una limpieza profunda.",599.00,"ELECTRODOMESTICOS");
		ProductRequest product4 = new ProductRequest("Cafetera Nespresso Vertuo","Cafetera automática compatible con cápsulas Vertuo.",149.90,"ELECTRODOMESTICOS");
		ProductRequest product5 = new ProductRequest("MacBook Air M2","Portátil ultraligero de Apple con chip M2 y batería de larga duración.",1349.00,"PORTATILES");
		ProductRequest product6 = new ProductRequest("Dell XPS 13","Laptop compacta con pantalla InfinityEdge y procesador Intel Core i7.",1299.00,"PORTATILES");
		ProductRequest product7 = new ProductRequest("PC Gaming MSI Aegis","Ordenador de escritorio con gráfica RTX 4070 y 32GB de RAM.",1799.00,"ORDENADORES");
		ProductRequest product8 = new ProductRequest("iMac 24”","Ordenador todo en uno con chip Apple M1 y pantalla Retina 4.5K.",1599.00,"ORDENADORES");
		ProductRequest product9 = new ProductRequest("Auriculares Sony WH-1000XM","Auriculares inalámbricos con cancelación activa de ruido.",399.99,"ASESORIOS");
		ProductRequest product10 = new ProductRequest("Teclado Mecánico Logitech G915","Teclado mecánico inalámbrico RGB para gaming.",249.00,"ASESORIOS");


		productService.saveProduct(product1);
		productService.saveProduct(product2);
		productService.saveProduct(product3);
		productService.saveProduct(product4);
		productService.saveProduct(product5);
		productService.saveProduct(product6);
		productService.saveProduct(product7);
		productService.saveProduct(product8);
		productService.saveProduct(product9);
		productService.saveProduct(product10);



	}

}
