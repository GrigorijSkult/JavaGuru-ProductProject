package shoppingList.repository;

import java.util.List;

public interface ProductRepository<T> {

    T addProduct(T newProduct);

    void removeProductByID(Long id);

    List<T> listOfAllProducts();

    T findProductByID(Long id);

    T updateProduct(Long id, T updatedProduct);

    boolean existsByName (T productEntity);

    boolean existsById (Long Id);
}