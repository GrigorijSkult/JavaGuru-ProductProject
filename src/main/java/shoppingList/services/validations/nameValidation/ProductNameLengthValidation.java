package shoppingList.services.validations.nameValidation;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductNameLengthValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getProductName().length() < 3 || productDto.getProductName().length() > 32) {
            throw new ProductValidationException("Product name cannot be less than 3 characters and more than 32");
        }
    }
}
