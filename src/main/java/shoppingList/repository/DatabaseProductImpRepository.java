package shoppingList.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import shoppingList.domain.ProductEntity;

import java.util.List;

@Repository
@Profile("mysql")
public class DatabaseProductImpRepository implements ProductRepository<ProductEntity> {

    @Override
    public ProductEntity addProduct(ProductEntity newProduct) {
        return null;
    }

    @Override
    public void removeProductByID(Long id) {

    }

    @Override
    public List<ProductEntity> listOfAllProducts() {
        return null;
    }

    @Override
    public ProductEntity findProductByID(Long id) {
        return null;
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        return null;
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
