package shoppingList.repository;

import shoppingList.domain.ProductEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductImpRepository implements Repository<ProductEntity> {

    private long newProductId = 1;
    private final Map<Long, ProductEntity> productsDB = new HashMap<>();

    //Main methods
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

    //Additional methods
    public boolean doesDbContainsSimilarProduct(ProductEntity productEntity) {
        for (ProductEntity value : productsDB.values()) {
            if (value.equals(productEntity)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesDbContainsId(Long id) {
        return productsDB.containsKey(id);
    }
}
