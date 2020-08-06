package shoppingList.product.services.businessLogic;

import shoppingList.product.domain.ProductEntity;
import shoppingList.product.services.validations.exception.ProductNotFoundException;

import java.util.List;

public interface TemplateService<T> {

    T addProductService(T newProductDto);

    boolean removeProductByIDService(Long id) throws ProductNotFoundException;

    List<T> listOfAllProductsService();

    T findProductByID(Long id) throws ProductNotFoundException;

    T updateProductService(Long id, ProductEntity updatedProductEntity) throws ProductNotFoundException;
}
