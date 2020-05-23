package shoppingList.repository;

import shoppingList.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductImpRepository implements Repository<Product> {

    private long newProductId = 1;
    private final Map<Long, Product> productsDB = new HashMap<>();

    //Main methods
    @Override
    public Product addProduct(Product newProduct) {
        newProduct.setProductId(newProductId);
        productsDB.put(newProductId, newProduct);
        newProductId++;
        return newProduct;
    }

    @Override
    public void removeProductByID(Long id) {
        productsDB.remove(id);
    }

    @Override
    public List<Product> listOfAllProducts() {
        return new ArrayList<>(productsDB.values());
    }

    @Override
    public Product findProductByID(Long id) {
        return productsDB.get(id);
    }

    //Additional methods
    public boolean doesDbContainsSimilarProduct(Product product) {
        for (Product value : productsDB.values()) {
            if (value.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesDbContainsId(Long id) {
        return productsDB.containsKey(id);
    }
}
