package shoppingList.services.validations;

import shoppingList.domain.ProductEntity;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountValidation implements ValidationRule<ProductEntity> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) < 0
                || productDto.getProductDiscount().compareTo(BigDecimal.valueOf(100.00)) > 0) {
            throw new ProductValidationException("Product discount value must be in the range from 0 to 100;");
        }
        if (productDto.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) != 0
                && productDto.getProductRegularPrice().compareTo(BigDecimal.valueOf(20.00)) < 0) {

            throw new ProductValidationException("A discount can be set for products with a price greater than 20.00 ");
        }
    }

}
