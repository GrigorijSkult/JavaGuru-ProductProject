package shoppingList.product.services.validations.discountValidation;

import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.validations.ValidationRule;
import shoppingList.product.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountRangeValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getDiscount().compareTo(BigDecimal.valueOf(0.00)) < 0
                || productDto.getDiscount().compareTo(BigDecimal.valueOf(100.00)) > 0) {
            throw new ProductValidationException("Product discount value must be in the range from 0 to 100");
        }
    }
}
