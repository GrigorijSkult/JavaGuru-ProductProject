package shoppingList.product.services.validations.discountValidation;

import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.validations.ValidationRule;
import shoppingList.product.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountPriceValueValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getDiscount().compareTo(BigDecimal.valueOf(0.00)) != 0
                && (productDto.getRegularPrice().compareTo(BigDecimal.valueOf(20.00)) < 0)) {
            throw new ProductValidationException("A discount can be set for products with a price greater than 20.00");
        }
    }
}
