package shoppingList.database;

import java.util.List;

public interface Repository<T> {

    void addProduct(T newProduct);

    void removeProductByID(Long id);

    List<T> listOfAllProducts();

    T findProductByID(Long id);

//    void updateProductInformation();

}
