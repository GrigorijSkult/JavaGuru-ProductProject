package shoppingList.services.validations.discountValidation;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

import java.util.ArrayList;
import java.util.List;

public class ProductDiscountValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        List<String> errorLogs = new ArrayList<>();
        List<ValidationRule<ProductDto>> validationRules = new ArrayList<>();
        validationRules.add(new ProductDiscountRangeValidation());
        validationRules.add(new ProductDiscountPriceValueValidation());

        for (ValidationRule<ProductDto> rule : validationRules) {
            try {
                rule.validate(productDto);
            } catch (ProductValidationException e) {
                errorLogs.add(e.getMessage());
            }
        }
        if (!errorLogs.isEmpty()) {
            throw new ProductValidationException(errorLogs.toString());
        }
    }
}
