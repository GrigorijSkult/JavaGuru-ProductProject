package shoppingList.repository;

import org.springframework.stereotype.Repository;
import shoppingList.domain.ProductEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductImpRepository implements ProductRepository<ProductEntity> {

    private long newProductId = 1;
    private final Map<Long, ProductEntity> productsDB = new HashMap<>();

    @Override
    public ProductEntity addProduct(ProductEntity newProductEntity) {
        ProductEntity productEntityCopy = new ProductEntity(newProductId, newProductEntity.getProductName(),
                newProductEntity.getProductRegularPrice(), newProductEntity.getProductCategory(), newProductEntity.getProductDiscount(),
                newProductEntity.getProductDescription());
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
    public ProductEntity findProductByID(Long id) {
        return productsDB.get(id);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        productsDB.replace(id, updatedProduct);
        return updatedProduct;
    }

    @Override
    public boolean existsByName(ProductEntity productEntity) {
        for (ProductEntity value : productsDB.values()){
            if (value.getProductName().equals(productEntity.getProductName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return productsDB.containsKey(id);
    }
}
