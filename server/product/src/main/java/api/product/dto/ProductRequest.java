package api.product.dto;

public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private String Categories;


    public ProductRequest(String name, String description, Double price, String categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        Categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", Categories='" + Categories + '\'' +
                '}';
    }
}
