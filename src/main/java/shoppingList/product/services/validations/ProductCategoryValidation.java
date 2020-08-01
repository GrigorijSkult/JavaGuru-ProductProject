package shoppingList.product.services.validations;

import org.springframework.stereotype.Component;
import shoppingList.product.domain.ProductCategory;
import shoppingList.product.dto.ProductDto;
import shoppingList.product.services.validations.exception.ProductValidationException;

@Component
public class ProductCategoryValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        boolean isValid = false;
        for (ProductCategory value : ProductCategory.values()) {
            if (productDto.getCategory().equals(value)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new ProductValidationException("Product category is incorrect");
        }
    }

    public void categoryNumberValidation(int productCategory) {
        if (productCategory < 0 || productCategory > ProductCategory.values().length - 1) {
            throw new ProductValidationException("Product category number is incorrect");
        }
    }
}
