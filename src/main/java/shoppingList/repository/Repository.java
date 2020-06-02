package shoppingList.repository;

import java.util.List;

public interface Repository<T> {

    T addProduct(T newProduct);

    void removeProductByID(Long id);

    List<T> listOfAllProducts();

    T findProductByID(Long id);

    T updateProduct(Long id, T updatedProduct);
}
