package shoppingList.services.validations;

import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.util.ArrayList;
import java.util.List;

public class ProductValidationService implements ValidationRule<ProductDto> {

    private final List<ValidationRule<ProductDto>> validationRules;

    public ProductValidationService(List<ValidationRule<ProductDto>> validationRules) {
        this.validationRules = validationRules;
    }

    private final List<String> errorLogs = new ArrayList<>();

    @Override
    public void validate(ProductDto productDto) {
        errorLogs.clear();
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
