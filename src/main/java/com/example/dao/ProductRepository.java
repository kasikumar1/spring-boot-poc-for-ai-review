import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Interface for Product Repository
interface IProductRepository {
    void addProduct(Product product);
    Optional<Product> getProductById(int id);
    List<Product> getAllProducts();
    void updateProduct(int id, Product product);
    void deleteProduct(int id);
}

// Product class
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // Validation methods
    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
    }

    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
    }
}

// Implementation of Product Repository
class ProductRepository implements IProductRepository {
    private List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public void updateProduct(int id, Product product) {
        deleteProduct(id);
        addProduct(product);
    }

    @Override
    public void deleteProduct(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}