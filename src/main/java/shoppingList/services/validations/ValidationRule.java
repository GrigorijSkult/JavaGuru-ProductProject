package shoppingList.services.validations;

import shoppingList.dto.ProductDto;

public interface ValidationRule<T> {

    void validate(ProductDto productDto);
}
