package shoppingList.services.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductValidationService implements ValidationRule<ProductDto> {

    private final List<ValidationRule<ProductDto>> validationRules;

    public ProductValidationService(List<ValidationRule<ProductDto>> validationRules) {
        this.validationRules = validationRules;
    }

    @Override
    public void validate(ProductDto productDto) {
        List<String> errorLogs = new ArrayList<>();
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
