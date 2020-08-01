package shoppingList.product.services.validations;

import shoppingList.product.dto.ProductDto;

public interface ValidationRule<T> {

    void validate(ProductDto productDto);
}
