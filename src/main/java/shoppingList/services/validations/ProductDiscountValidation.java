package shoppingList.services.validations;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDiscountValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        List<String> errorLogs = new ArrayList<>();
        try {
            discountRangeValidation(productDto);
        }catch (ProductValidationException e){
            errorLogs.add(e.getMessage());
        }
        try {
            discountPriceValueValidation(productDto);
        }catch (ProductValidationException e){
            errorLogs.add(e.getMessage());
        }
        if (!errorLogs.isEmpty()) {
            throw new ProductValidationException(errorLogs.toString());
        }
    }

    private void discountRangeValidation(ProductDto productDto) {
        if (productDto.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) < 0
                || productDto.getProductDiscount().compareTo(BigDecimal.valueOf(100.00)) > 0) {
            throw new ProductValidationException("Product discount value must be in the range from 0 to 100");
        }
    }

    private void discountPriceValueValidation(ProductDto productDto) {
        if (productDto.getProductDiscount().compareTo(BigDecimal.valueOf(0.00)) != 0
                && (productDto.getProductRegularPrice().compareTo(BigDecimal.valueOf(20.00)) < 0)) {
            throw new ProductValidationException("A discount can be set for products with a price greater than 20.00");
        }
    }
}
