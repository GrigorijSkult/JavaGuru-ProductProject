package shoppingList.services.validations;

import shoppingList.domain.ProductCategory;
import shoppingList.dto.ProductDto;
import shoppingList.services.validations.exception.ProductValidationException;

public class ProductCategoryValidation implements ValidationRule<ProductDto> {

    @Override
    public void validate(ProductDto productDto) {
        boolean isValid = false;
        for (ProductCategory value : ProductCategory.values()) {
            if (productDto.getProductCategory().equals(value)) {
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
