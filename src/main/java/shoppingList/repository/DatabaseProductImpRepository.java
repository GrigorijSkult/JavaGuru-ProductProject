package shoppingList.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import shoppingList.domain.ProductEntity;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@Profile("mysql")
public class DatabaseProductImpRepository implements ProductRepository {

    @Override
    public ProductEntity addProduct(ProductEntity newProduct) {
        return null;
    }

    @Override
    public Optional<ProductEntity> removeProductByID(Long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<ProductEntity> listOfAllProducts() {
        return null;
    }

    @Override
    public Optional<ProductEntity> findProductByID(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductEntity> updateProduct(Long id, ProductEntity updatedProduct) {
        return Optional.empty();
    }

    @Override
    public boolean existsByName(ProductEntity productEntity) {
        return false;
    }

    @Override
    public boolean existsById(Long Id) {
        return false;
    }
}
