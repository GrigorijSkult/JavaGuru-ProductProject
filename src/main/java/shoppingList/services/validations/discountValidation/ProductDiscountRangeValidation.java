package shoppingList.services.validations.discountValidation;

import shoppingList.constants.ValidationConstants;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountRangeValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getDiscount().compareTo(BigDecimal.valueOf(ValidationConstants.minProductDiscountValue)) < 0
                || productDto.getDiscount().compareTo(BigDecimal.valueOf(ValidationConstants.maxProductDiscountValue)) > 0) {
            throw new ProductValidationException("Product discount value must be in the range from " + ValidationConstants.minProductDiscountValue + " to "
                    + ValidationConstants.maxProductDescriptionLength);
        }
    }
}
