package shoppingList.product.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import shoppingList.product.domain.ProductEntity;

import java.util.*;

@Repository
@Profile("inmemory")
public class InMemoryProductImpRepository implements ProductRepository {

    private long newProductId = 1;
    private final Map<Long, ProductEntity> productsDB = new HashMap<>();

    @Override
    public ProductEntity addProduct(ProductEntity newProductEntity) {
        ProductEntity productEntityCopy = new ProductEntity(newProductId, newProductEntity.getName(),
                newProductEntity.getRegularPrice(), newProductEntity.getCategory(), newProductEntity.getDiscount(),
                newProductEntity.getDescription());
        productsDB.put(newProductId, productEntityCopy);
        newProductId++;
        return productEntityCopy;
    }

    @Override
    public void removeProductByID(Long id) {
        productsDB.remove(id);
    }

    @Override
    public List<ProductEntity> listOfAllProducts() {
        return new ArrayList<>(productsDB.values());
    }

    @Override
    public Optional<ProductEntity> findProductByID(Long id) {
        return Optional.ofNullable(productsDB.get(id));
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        productsDB.replace(id, updatedProduct);
        return updatedProduct;
    }

    @Override
    public Optional<ProductEntity> findProductByName(String name) {
        for (ProductEntity value : productsDB.values()) {
            if (value.getName().equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return productsDB.containsKey(id);
    }
}
