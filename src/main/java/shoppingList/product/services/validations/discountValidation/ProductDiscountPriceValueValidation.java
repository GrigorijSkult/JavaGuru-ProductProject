package shoppingList.services.validations.discountValidation;

import shoppingList.constants.ValidationConstants;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountPriceValueValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getDiscount().compareTo(BigDecimal.valueOf(0.00)) != 0
                && (productDto.getRegularPrice().compareTo(BigDecimal.valueOf(ValidationConstants.minPriceForDiscount)) < 0)) {
            throw new ProductValidationException("A discount can be set for products with a price greater than " + ValidationConstants.minPriceForDiscount);
        }
    }
}
