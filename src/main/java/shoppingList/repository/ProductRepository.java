package shoppingList.repository;

import shoppingList.domain.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    ProductEntity addProduct(ProductEntity newProduct);

    Optional<ProductEntity> removeProductByID(Long id);

    List<ProductEntity> listOfAllProducts();

    Optional<ProductEntity> findProductByID(Long id);

    ProductEntity updateProduct(Long id, ProductEntity updatedProduct);

    boolean existsByName(ProductEntity productEntity);

    boolean existsById(Long Id);
}