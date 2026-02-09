import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements JpaRepository<Product, Long> {

    private List<Product> products = new ArrayList<>();

    // CRUD operations
    @Override
    public <S extends Product> S save(S entity) {
        products.add(entity);
        return entity;
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return products.stream().filter(product -> product.getId().equals(aLong)).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void deleteById(Long aLong) {
        products.removeIf(product -> product.getId().equals(aLong));
    }

    // Additional methods can be added as needed
}