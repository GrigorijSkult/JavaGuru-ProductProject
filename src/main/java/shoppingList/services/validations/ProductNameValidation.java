package shoppingList.services.validations;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductNameValidation implements ValidationRule<ProductDto> {

    String errorMassage = "Product name cannot be less than 3 characters and more than 32;";

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getProductName().length() < 3 || productDto.getProductName().length() > 32) {
            throw new ProductValidationException(errorMassage);
        }
    }

}
