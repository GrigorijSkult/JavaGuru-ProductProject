package shoppingList.product.repository;

import shoppingList.product.domain.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    ProductEntity addProduct(ProductEntity newProduct);

    boolean removeProductByID(Long id);

    List<ProductEntity> listOfAllProducts();

    Optional<ProductEntity> findProductByID(Long id);

    ProductEntity updateProduct(Long id, ProductEntity updatedProduct);

    Optional<ProductEntity> findProductByName(String name);

    boolean existsById(Long Id);
}