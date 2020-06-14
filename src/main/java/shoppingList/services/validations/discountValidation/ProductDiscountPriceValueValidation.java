package shoppingList.services.validations.discountValidation;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountPriceValueValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) != 0
                && (productDto.getProductRegularPrice().compareTo(BigDecimal.valueOf(20.00)) < 0)) {
            throw new ProductValidationException("A discount can be set for products with a price greater than 20.00");
        }
    }
}
