package shoppingList.product.services.validations.nameValidation;

import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.validations.ValidationRule;
import shoppingList.product.services.validations.exception.ProductValidationException;

public class ProductNameLengthValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getName().length() < 3 || productDto.getName().length() > 32) {
            throw new ProductValidationException("Product name cannot be less than 3 characters and more than 32");
        }
    }
}
