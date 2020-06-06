package shoppingList.services.validations;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductPriceValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        if (productDto.getProductRegularPrice().compareTo(BigDecimal.valueOf(0.00)) < 0) {
            throw new ProductValidationException("Product price must be greater than 0");
        }
    }

}
