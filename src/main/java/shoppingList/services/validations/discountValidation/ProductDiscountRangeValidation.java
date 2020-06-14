package shoppingList.services.validations.discountValidation;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountRangeValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) < 0
                || productDto.getProductDiscount().compareTo(BigDecimal.valueOf(100.00)) > 0) {
            throw new ProductValidationException("Product discount value must be in the range from 0 to 100");
        }
    }
}
