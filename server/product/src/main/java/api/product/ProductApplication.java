package api.product;

import api.product.entity.Category;
import api.product.entity.Product;
import api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(productService.getAllProduct() != null){
			productService.deleteProduct();
		}
		Product product = new Product("Lenovo IdeaPad 3",
				"Un portátil versátil para tareas diarias, con pantalla de 15.6 Full HD, procesador AMD Ryzen 5, 8 GB de RAM y 512 GB SSD. Ideal para estudiantes y profesionales que buscan un rendimiento confiable.",
				599.99,
				Category.PORTATILES,
				1);
		Product product1 = new Product("Samsung Galaxy S23",
				"El Samsung Galaxy S23 cuenta con una pantalla Dynamic AMOLED de 6.1, cámara de 50 MP, procesador Snapdragon 8 Gen 2, 8 GB de RAM y 128 GB de almacenamiento interno. Potente y elegante, ideal para los amantes de la tecnología.",
				899.99,
				Category.MOBILES,
				1
		);
		Product product2 = new Product("HP Pavilion 590",
				"PC de sobremesa HP Pavilion 590, con procesador Intel Core i5, 8 GB de RAM, 512 GB SSD y tarjeta gráfica integrada Intel UHD. Perfecto para uso general y entretenimiento en casa.",
				499.99,
				Category.SOBREMESAS,
				1);
		Product product3 = new Product("Whirlpool WRS588FIHZ",
				"Refrigerador de 3 puertas con tecnología de control de temperatura avanzado. Cuenta con un sistema de ahorro energético y una capacidad de 27.6 pies cúbicos, ideal para familias grandes.",
				1299.99,
				Category.ELECTRODOMESTICO,
				1);
		Product product4 = new Product("Logitech MX Master 3",
				"El mouse inalámbrico Logitech MX Master 3 es perfecto para profesionales que necesitan un rendimiento preciso. Con 7 botones personalizables, rueda de desplazamiento de precisión y batería de larga duración.",
				99.99,
				Category.ACESORIOS,
				1);

		productService.savePrivateMethod(product);
		productService.savePrivateMethod(product1);
		productService.savePrivateMethod(product2);
		productService.savePrivateMethod(product3);
		productService.savePrivateMethod(product4);



	}
}
