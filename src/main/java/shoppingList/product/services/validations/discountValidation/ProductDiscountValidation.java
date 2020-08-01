package shoppingList.product.services.validations.discountValidation;

import org.springframework.stereotype.Component;
import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.validations.ValidationRule;
import shoppingList.product.services.validations.exception.ProductValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
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
