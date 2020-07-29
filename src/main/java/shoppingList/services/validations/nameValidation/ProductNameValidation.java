package shoppingList.services.validations.nameValidation;

import org.springframework.stereotype.Component;
import shoppingList.dto.ProductDto;
import shoppingList.mappers.ProductMapper;
import shoppingList.repository.ProductRepository;
import shoppingList.services.validations.ValidationRule;
import shoppingList.services.validations.exception.ProductValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductNameValidation implements ValidationRule<ProductDto> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductNameValidation(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void validate(ProductDto productDto) {
        List<String> errorLogs = new ArrayList<>();
        List<ValidationRule<ProductDto>> validationRules = new ArrayList<>();
        validationRules.add(new ProductNameLengthValidation());
        validationRules.add(new ProductUniqueNameValidation(productRepository, productMapper));

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
