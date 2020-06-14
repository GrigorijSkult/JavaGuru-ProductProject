package shoppingList.services.businessLogic;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductNotFoundException;

import java.util.List;

public interface TemplateService<T> {

    ProductDto addProductService(ProductDto newProductDto);

    boolean removeProductByIDService(Long id) throws ProductNotFoundException;

    List<T> listOfAllProductsService();

    T findProductByID(Long id) throws ProductNotFoundException;

    ProductDto updateProductService(Long id, ProductDto updatedProductDto) throws ProductNotFoundException;
}
