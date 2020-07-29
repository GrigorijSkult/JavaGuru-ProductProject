package shoppingList.services.validations.nameValidation;

import shoppingList.constants.ValidationConstants;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductNameLengthValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getName().length() < ValidationConstants.minProductNameLength || productDto.getName().length() > ValidationConstants.maxProductNameLength) {
            throw new ProductValidationException("Product name cannot be less than " + ValidationConstants.minProductNameLength
                    + " characters and more than " + ValidationConstants.maxProductNameLength);
        }
    }
}
