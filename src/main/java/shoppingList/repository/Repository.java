package shoppingList.repository;

import shoppingList.domain.Product;

import java.util.List;

public interface Repository<T> {

    Product addProduct(T newProduct);

    void removeProductByID(Long id);

    List<T> listOfAllProducts();

    T findProductByID(Long id);

//    void updateProductInformation(Long id);

}
