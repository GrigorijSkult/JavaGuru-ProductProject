package shoppingList.services.validations;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountValidation implements ValidationRule<ProductDto> {

    public String discountRangeErrorMessage = "Product discount value must be in the range from 0 to 100;";
    public String discountPriceErrorMessage = "A discount can be set for products with a price greater than 20.00;";

    @Override
    public void validate(ProductDto productDto) {
        String errorLogs = null;
        if (productDto.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) < 0
                || productDto.getProductDiscount().compareTo(BigDecimal.valueOf(100.00)) > 0) {
            errorLogs = discountRangeErrorMessage;
        }
        if (productDto.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) != 0
                && (productDto.getProductRegularPrice().compareTo(BigDecimal.valueOf(20.00)) < 0)) {
            if (errorLogs != null) {
                errorLogs = errorLogs + ", " + discountPriceErrorMessage;
            } else {
                errorLogs = discountPriceErrorMessage;
            }
        }
        if (errorLogs != null) {
            throw new ProductValidationException(errorLogs);
        }
    }
}
