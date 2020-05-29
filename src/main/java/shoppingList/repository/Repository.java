package shoppingList.repository;

import shoppingList.domain.ProductEntity;

import java.util.List;

public interface Repository<T> {

    ProductEntity addProduct(T newProduct);

    void removeProductByID(Long id);

    List<T> listOfAllProducts();

    T findProductByID(Long id);

}
